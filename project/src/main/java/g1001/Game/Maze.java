package g1001.Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import g1001.Direction.*;

import g1001.Game.Elements.Fixed.Collectables.Fruit;
import g1001.Game.Elements.Fixed.Collectables.Pellet;
import g1001.Game.Elements.Fixed.Collectables.PowerUp;
import g1001.Game.Elements.Fixed.Gate;
import g1001.Game.Elements.Fixed.Wall;
import g1001.Game.Elements.Moving.Ghosts.Ghost;
import g1001.Game.Elements.Moving.Ghosts.HostileGhost;
import g1001.Game.Elements.Moving.Ghosts.ScaredGhost;
import g1001.Game.Elements.Moving.PacMan;
import g1001.State.GameOverState;
import g1001.State.State;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maze {
    private final int cols, rows;
    private PacMan pacman;

    private List<Gate> gates;
    private List<Pellet> pellets;
    private List<PowerUp> powerups;
    private List<Fruit> fruits;
    private List<HostileGhost> hostileGhosts;
    private List<ScaredGhost> scaredGhosts;
    private List<Wall> walls;
    private HashMap<Position, Boolean> isThereWall;
    private HashMap<Position, Boolean> isThereScaredGhost;
    private HashMap<Position, Boolean> isThereHostileGhost;
    private HashMap<Position, Boolean> isThereGate;

    private int currLevel;
    private GameInfo info;
    private int totalPoints;

    /**
     * CONSTRUCTOR
     **/
    public Maze(int cols, int rows) throws IOException {
            this.rows = rows;
            this.cols = cols;
            this.pacman = new PacMan(new Position(0, 0), new North());

            this.pellets = new ArrayList<>();
            this.powerups = new ArrayList<>();
            this.fruits = new ArrayList<>();
            this.hostileGhosts = new ArrayList<>();
            this.scaredGhosts = new ArrayList<>();
            this.walls = new ArrayList<>();
            this.gates = new ArrayList<>();
            this.isThereWall = new HashMap<Position, Boolean>();
            this.isThereScaredGhost = new HashMap<Position, Boolean>();
            this.isThereHostileGhost = new HashMap<Position, Boolean>();
            this.isThereGate = new HashMap<Position, Boolean>();
            this.info = new GameInfo();
    }


    /**
     * GETTERS
     **/
    public PacMan getPacman() {
        return pacman;
    }

    public int getLevel() {
        return currLevel;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    public void addWall (Wall wall)
    {
        walls.add(wall);
        isThereWall.put(wall.getPosition(), true);
    }

    public void addHostileGhost (HostileGhost ghost)
    {
        hostileGhosts.add(ghost);
        isThereHostileGhost.put(ghost.getPosition(), true);

    }
    public void addScaredGhost (ScaredGhost ghost)
    {
        scaredGhosts.add(ghost);
        isThereScaredGhost.put(ghost.getPosition(), true);

    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public List<Pellet> getPellets() {
        return pellets;
    }

    public List<PowerUp> getPowerups() {
        return powerups;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public List<HostileGhost> getHostileGhosts() {
        return hostileGhosts;
    }

    public List<ScaredGhost> getScaredGhosts() {
        return scaredGhosts;
    }


    /** DRAW METHOD **/

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);

        pacman.draw(graphics);

        for (Pellet pellet : pellets) {
            pellet.draw(graphics);
        }

        for (PowerUp powerup : powerups) {
            powerup.draw(graphics);
        }

        for (Fruit fruit : fruits) {
            fruit.draw(graphics);
        }

        for (Ghost ghost : hostileGhosts) {
            ghost.draw(graphics);
        }

        for (Ghost ghost : scaredGhosts) {
            ghost.draw(graphics);
        }

        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Gate gate : gates) {
            gate.draw(graphics);
        }


        info.draw(graphics, this);
    }


    /** MAZE-RELATED METHODS **/

    public void readMaze(int level) throws IOException {
        currLevel = level;
        String mazepath = "src/main/resources/mazes/level" + level +".txt";
        try {
            Reader f = Files.newBufferedReader(Paths.get(mazepath), StandardCharsets.UTF_8);
            int c;
            int cnt = 0;

            while ((c = f.read()) != -1) {
                buildMaze((char) c, cnt);
                //System.out.println("Coordinates (x,y): " + cnt % cols + ", " + cnt/cols + " " + (char)c);

                if ((char) c != '\n' && (char) c != '\r')
                    cnt++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("An exception has occurred.");
        }
    }

    private void buildMaze(char c, int cnt) {
        Direction direction = new North();
        switch (c) {
            case '#' -> {
                Wall wall = new Wall(new Position(cnt % cols, cnt / cols));
                walls.add(wall);
                isThereWall.put(wall.getPosition(), true);
            }

            case '@' -> powerups.add(new PowerUp(new Position(cnt % cols, cnt / cols)));
            case '.' -> pellets.add(new Pellet(new Position(cnt % cols, cnt / cols)));
            case 'M' -> {
                HostileGhost hostileGhost = new HostileGhost(new Position(cnt % cols, cnt / cols), direction);
                hostileGhosts.add(hostileGhost);
                isThereHostileGhost.put(hostileGhost.getPosition(), true);
            }
            case 'G' ->{
                Gate gate = new Gate(new Position(cnt % cols, cnt / cols));
                gates.add(gate);
                isThereGate.put(gate.getPosition(), true);
            }
            case 'P' -> setPacman(new PacMan(new Position(cnt % cols, cnt / cols), direction));
            case '&' -> fruits.add(new Fruit(new Position(cnt % cols, cnt/cols)));

            default -> {}
        }
    }


    /** INPUT RELATED METHOD **/

    public State processKey(KeyStroke key) throws IOException {
        /** THIS IS WHERE WE PROCESS THE KEY AND ACT ACCORDINGLY TO IT **/
        switch (key.getKeyType()) {
            case ArrowUp -> {
                pacman.setDirection(new North());
                pacman.setC("V");
            }
            case ArrowDown -> {
                pacman.setDirection(new South());
                pacman.setC("^");
            }
            case ArrowLeft -> {
                pacman.setDirection(new West());
                pacman.setC(">");
            }
            case ArrowRight -> {
                pacman.setDirection(new East());
                pacman.setC("<");
            }
            default -> {}
        }

        movePacman(pacman.move());
        processGate();

        if (wasGhostEaten())
        {
            ghostRegen();
        }

        if (wasPacmanEaten()){
            totalPoints += info.getPoints();
            return new GameOverState(totalPoints);
        }

        retrieveCollectables();

        // If the player won
        if (playerWon()){
            totalPoints += info.getPoints();

            // Only writes to the leaderboard if the level is the last
            if (currLevel == 5)
                return new GameOverState(totalPoints);
            nextLevel();
        }
        return null;
    }


    /** PACMAN-RELATED METHODS **/

    public boolean pacmanCanMove(Position position) {
        return !isThereWall.containsKey(position);
    }

    public void movePacman(Position position) {
        if (pacmanCanMove(position))
            pacman.setPosition(position);

        moveScaredGhosts();
        moveHostileGhosts();
    }

    public boolean wasPacmanEaten() {
        return isThereHostileGhost.containsKey(pacman.getPosition());
    }

    public boolean wasGhostEaten() {
        return isThereScaredGhost.containsKey(pacman.getPosition());
    }

    public void ghostRegen()
    {
        for (ScaredGhost ghost: scaredGhosts)
        {

            if (ghost.getPosition().equals(pacman.getPosition()))
            {
                addHostileGhost(new HostileGhost(new Position(14 ,14), new South()));
                scaredGhosts.remove(ghost);
                return;
            }
        }
        return;
    }




    /**************************** GHOST-RELATED METHODS ****************************/

    /**
     * Will do all the necessary logic to move a hostile ghost
     */
    public void moveHostileGhosts() {

        for (HostileGhost ghost : hostileGhosts) {
            Position ghostPos, pacmanPos;

            // Auxiliary variables
            ghostPos = ghost.getPosition();
            pacmanPos = pacman.getPosition();

            if (ghostPos.getY() < pacmanPos.getY()) {
                if(manageGhostMovement(ghost, new South()))
                    continue;
            }

            if (ghostPos.getY() > pacmanPos.getY()) {

                if (manageGhostMovement(ghost, new North()))
                {
                    continue;
                }

            }

            if (ghostPos.getX() > pacmanPos.getX()) {
                if(manageGhostMovement(ghost, new West()))
                    continue;
            }

            if (ghostPos.getX() < pacmanPos.getX()) {
                if(manageGhostMovement(ghost, new East()))
                    continue;
            }
            //If the ghost did not move

            if (!ghost.getPosition().equals(pacmanPos))
                forceMovement(ghost);
        }

    }

    /**
     * Will do all the necessary logic to move a scared ghost
     */
    public void moveScaredGhosts() {
        for (ScaredGhost ghost : scaredGhosts) {
            Position ghostPos, pacmanPos;

            // Auxiliary variables
            ghostPos = ghost.getPosition();
            pacmanPos = pacman.getPosition();

            if (ghostPos.getY() < pacmanPos.getY()) {

                if (manageGhostMovement(ghost, new North()))
                {
                    continue;
                }

            }

            if (ghostPos.getY() > pacmanPos.getY()) {
                if (manageGhostMovement(ghost, new South()))
                    continue;

            }

            if (ghostPos.getX() > pacmanPos.getX()) {
                if(manageGhostMovement(ghost, new East()))
                    continue;
            }

            if (ghostPos.getX() < pacmanPos.getX()) {
                if(manageGhostMovement(ghost, new West()))
                    continue;
            }

            if (!ghost.getPosition().equals(pacmanPos))
                forceMovement(ghost);
        }
    }

    public void forceMovement(Ghost ghost)
    {
        if (manageGhostMovement(ghost, new East())) return;
        if (manageGhostMovement(ghost, new West())) return;
        if (manageGhostMovement(ghost, new North())) return;
        if (manageGhostMovement(ghost, new South())) return;
    }


    /**
     * Will update the position depending on the direction
     * and will call the makeGhostMove method
     * @param ghost ghost to move
     * @param direction direction to the ghost to
     */
    public boolean manageGhostMovement(Ghost ghost, Direction direction){

        Position pos = ghost.getPosition();

        if (direction instanceof North){
            return makeGhostMove(new Position(pos.getX(), pos.getY() -1), ghost, direction);
        }

        else if (direction instanceof South){
            return makeGhostMove(new Position(pos.getX(), pos.getY() + 1), ghost, direction);
        }

        else if (direction instanceof West){
            return makeGhostMove(new Position(pos.getX() - 1, pos.getY()), ghost, direction);
        }

        else{
            return makeGhostMove(new Position(pos.getX() + 1, pos.getY()), ghost, direction);
        }
    }

    /**
     * Will check if the ghost can move, if true, will remove the
     * respective entry from hashmap, move the ghost, and update
     * the hashmap with the new position
     *
     * @param position hashmap containing the positions of some object
     * @param ghost element we want to move
     * @param direction direction that the element is gonna acquire
     */
    public boolean makeGhostMove(Position position, Ghost ghost, Direction direction){
        if (!willGhostCollide(position)){
            boolean type = ghost instanceof ScaredGhost;

            // Remove the old entry from the hashmap
            if (type)
                isThereScaredGhost.remove(ghost.getPosition());
            else
                isThereHostileGhost.remove(ghost.getPosition());

            // Set the ghosts' new position
            ghost.setDirection(direction);
            ghost.setPosition(position);

            // Add the new entry to the hashmap
            if (type)
                isThereScaredGhost.put(ghost.getPosition(), true);
            else
                isThereHostileGhost.put(ghost.getPosition(),true);

            return true;
        }
        return false;
    }

    /**
     * Checks whether the position is occupied with either
     * a wall or another ghost
     *
     * @param  position the position which the ghost will move too
     * @return true if the position is occupied, and ghost can´t move
     */
    public boolean willGhostCollide(Position position){
        if (checkHashKey(isThereScaredGhost, position)) return true;
        if (checkHashKey(isThereWall, position)) return true;
        if (checkHashKey(isThereGate, position)) return true;
        return checkHashKey(isThereHostileGhost, position);
    }

    /**
     * Checks whether the there is a key in the map that matches the value of pos
     * @param map hashmap containing the positions of some object
     * @param pos will check if any of the hashmap keys has pos in its value
     * @return true if there is a match between the map and the position
     */
    public boolean checkHashKey(HashMap<Position, Boolean> map, Position pos){
        return map.containsKey(pos);
    }

    /**
     * If the pacman ate a power-up, all the ghosts will become scared, and keep that state for
     * some time
     */
    public void processPowerUp() {
        for (int i = 0; i < hostileGhosts.size(); i++) {
            // Adds a scared ghost in the position where there should be an hostile one
            ScaredGhost scaredGhost = new ScaredGhost(hostileGhosts.get(i).getPosition(), new North());
            scaredGhosts.add(scaredGhost);
            isThereScaredGhost.put(scaredGhost.getPosition(), true);
        }
        // Clears the hostile ghost list
        hostileGhosts.clear();
        isThereHostileGhost.clear();

    }

    /**
     * Revert the scared ghosts back to their original state
     */
    public void reverseGhosts() {
        for (int i = 0; i < scaredGhosts.size(); i++) {
            // Adds a scared ghost in the position where there should be an hostile one
            HostileGhost hostileGhost = new HostileGhost(scaredGhosts.get(i).getPosition(), new North());
            hostileGhosts.add(hostileGhost);
            isThereHostileGhost.put(hostileGhost.getPosition(), true);
        }
        // Clears the hostile ghost list
        scaredGhosts.clear();
        isThereScaredGhost.clear();

    }

    public boolean playerWon() {
        return (pellets.size() == 0);
    }


    /** GAME-ELEMENTS RELATED METHODS **/
    public void retrieveCollectables() {
        retrievePellets();
        retrieveFruits();
        retrievePowerUps();
    }

    public void retrievePellets(){
        for (Pellet pellet : pellets) {
            if (pacman.getPosition().equals(pellet.getPosition())) {
                info.managePoints(pellet);
                pellets.remove(pellet);
                break;
            }
        }
    }

    public void retrieveFruits(){
        for (Fruit fruit : fruits) {
            if (pacman.getPosition().equals(fruit.getPosition())) {
                info.managePoints(fruit);
                fruits.remove(fruit);
                break;
            }
        }
    }

    public void retrievePowerUps(){
        for (PowerUp powerup : powerups) {
            if (pacman.getPosition().equals(powerup.getPosition())) {
                info.managePoints(powerup);
                powerups.remove(powerup);
                processPowerUp();
                break;
            }
        }}

    public void processGate() {
        Position posNew;
        for (Gate gate : gates) {
            if (pacman.getPosition().equals(gate.getPosition())) {
                if (gate.getSide() == 'L')
                    posNew = new Position(pacman.getPosition().getX() + (cols - 2), pacman.getPosition().getY());
                else if (gate.getSide() == 'R')
                    posNew = new Position(pacman.getPosition().getX() - (cols - 2), pacman.getPosition().getY());
                else if (gate.getSide() == 'T')
                    posNew = new Position(pacman.getPosition().getX(), pacman.getPosition().getY() + (rows - 1));
                else
                    posNew = new Position(pacman.getPosition().getX(), pacman.getPosition().getY() - (rows - 1));
                pacman.setPosition(posNew);
            }
        }
    }


    /** PREPARING THE NEXT LEVEL **/
    public void nextLevel() throws IOException {
        // Will clear all the current elements lists
        gates.clear();
        pellets.clear();
        powerups.clear();
        hostileGhosts.clear();
        scaredGhosts.clear();
        walls.clear();
        isThereWall.clear();
        isThereHostileGhost.clear();
        isThereScaredGhost.clear();

        // Don´t know if this is necessary, but it does no harm
        pacman = null;

        // Clears the info about the game
        info = new GameInfo();

        // Updates the current level number
        currLevel++;

        // Will read from the new level file and start all over again
        this.readMaze(currLevel);
    }

}