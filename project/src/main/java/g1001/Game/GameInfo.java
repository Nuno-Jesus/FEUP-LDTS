package g1001.Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Game.Elements.Fixed.Collectables.Collectable;
import g1001.Game.Elements.Fixed.Collectables.Fruit;
import g1001.Game.Elements.Fixed.Collectables.Pellet;
import g1001.Game.Elements.Fixed.Collectables.PowerUp;

public class GameInfo {
    private int points;
    private int eatenPowerUps;
    private int eatenPellets;
    private int eatenFruits;

    public GameInfo(){
        this.points = 0;
        this.eatenPowerUps = 0;
        this.eatenFruits = 0;
        this.eatenPellets = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void managePoints (Collectable collectable){
        if      (collectable instanceof Fruit)   eatenFruits++;
        else if (collectable instanceof Pellet)  eatenPellets++;
        else if (collectable instanceof PowerUp) eatenPowerUps++;


        this.points += collectable.getPoints();
    }

    public void draw (TextGraphics graphics, Maze maze){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(31, 0), "You have collected: " + eatenPowerUps + " powerUps");
        graphics.putString(new TerminalPosition(31, 1), "You have collected: " + eatenPellets  + " pac-dots");
        graphics.putString(new TerminalPosition(31, 2), "You have eaten:     " + eatenFruits   + " fruits");

        graphics.putString(new TerminalPosition(31,4),  "Your points in the current level: " + points );
        graphics.putString(new TerminalPosition(31, 5), "Your accumulated points:          " + maze.getTotalPoints());

        graphics.putString(new TerminalPosition(31, 7), "State of the ghosts: ");
        if(maze.getScaredGhosts().isEmpty()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#AA0000"));
            graphics.putString(new TerminalPosition(52, 7), "Hostile");
        }
        else{
            graphics.setForegroundColor(TextColor.Factory.fromString("#0000AA"));
            graphics.putString(new TerminalPosition(52, 7), "Scared");
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(31, 9), "Current level: " + maze.getLevel());

        drawInstructions(graphics);

    }

    public void drawInstructions(TextGraphics graphics){
        graphics.putString(new TerminalPosition(31, 12), "Press ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#fff717"));
        graphics.putString(new TerminalPosition(37, 12),"'M'");
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(41, 12),"to stop the music");

        graphics.putString(new TerminalPosition(31, 13), "Press ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#fff717"));
        graphics.putString(new TerminalPosition(37, 13),"'P'");
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(41, 13),"to play the music");

        graphics.putString(new TerminalPosition(31, 15), "Press ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#fff717"));
        graphics.putString(new TerminalPosition(37, 15),"'ESCAPE'");
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(46, 15), "to pause the game");
    }
}

