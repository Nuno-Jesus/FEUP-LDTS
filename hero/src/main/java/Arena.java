import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    /******************* ATTRIBUTES *******************/

    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    /******************* CONSTRUCTOR ******************/

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(10, 10));
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    /******************** METHODS *********************/

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));

            //If the coin is spawned on the hero, it replaces that one
            while(coins.get(i).getPosition().equals(hero.getPosition())){
                coins.remove(i);
                coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }

            //Compares the recently added coin with the ones added before
            for(int k = 0; k < i; k++){
                while(coins.get(k).getPosition().equals(coins.get(i).getPosition())){
                    coins.remove(i);
                    coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
                }
            }
        }

        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));

        return monsters;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private void moveMonsters(){
        for(Monster monster : monsters){
            Position position = monster.move();

            if(canMonsterMove(position))
                monster.setPosition(position);
        }
    }

    private void moveHero(Position position){
        if(canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    private void verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(hero.getPosition().equals(monster.getPosition())){
                System.out.println("\n\t---- GAME OVER: A MONSTER ATE YOU! ----");
                System.exit(0);
            }
        }
    }

    private void retrieveCoins(){
        for(Coin coin: coins){
            if(hero.getPosition().equals(coin.getPosition())){
                coins.remove(coin);
                break;
            }
        }
    }

    private boolean canHeroMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }

        return position.getX() > 0 && position.getX() < width && position.getY() > 0 && position.getY() < height;
    }

    private boolean canMonsterMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }

        return position.getX() > 0 && position.getX() < width && position.getY() > 0 && position.getY() < height;
    }

    public void processKey(KeyStroke key){
        System.out.println(key);

        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
            case ArrowLeft -> moveHero(hero.moveLeft());
            case ArrowRight -> moveHero(hero.moveRight());
        }

        retrieveCoins();

        verifyMonsterCollisions();
        moveMonsters();
        verifyMonsterCollisions();
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#660000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);
        for(Wall wall : walls)
            wall.draw(graphics);

        for(Coin coin : coins)
            coin.draw(graphics);

        for(Monster monster : monsters)
            monster.draw(graphics);
    }
}
