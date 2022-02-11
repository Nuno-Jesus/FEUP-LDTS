package g1001;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g1001.Direction.North;
import g1001.Direction.Position;
import g1001.Direction.South;
import g1001.Game.Elements.Fixed.Collectables.Pellet;
import g1001.Game.Elements.Fixed.Gate;
import g1001.Game.Elements.Fixed.Wall;
import g1001.Game.Elements.Moving.Ghosts.HostileGhost;
import g1001.Game.Elements.Moving.Ghosts.ScaredGhost;
import g1001.Game.Elements.Moving.PacMan;
import g1001.Game.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class MazeTest {

    private Maze maze;

    @BeforeEach
    public void setup() throws IOException {
        maze = new Maze(28, 30);
    }

    @Test
    public void checkIfReads() throws IOException {
        maze.readMaze(1);
        Assertions.assertEquals(maze.getPacman().getPosition().getX(),  14);
        Assertions.assertEquals(maze.getPacman().getPosition().getY(),  23);
    }

    @Test
    public void checkIfMazeDrawn() throws IOException {
        try{
            TerminalSize terminalSize = new TerminalSize(160, 160);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();
            maze.setPacman(new PacMan(new Position(14, 23), new South()));
            maze.draw(graphics);

            // Pac-Man is being drawn to its correct position!
            Assertions.assertEquals(graphics.getCharacter(14, 23).getCharacterString(), "V");
        }catch(IOException e){
            System.out.println("IO Exception");
        }
    }

    @Test
    public void checkProcessingKey() throws IOException {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        maze.processKey(key);
        String c = maze.getPacman().getC();
        Assertions.assertEquals("V", c);
    }


    @Test
    public void checkPlayerWon(){
        maze.getPellets().add(new Pellet(new Position(1,1)));
        Assertions.assertNotEquals(maze.getPellets().size(), 0);
        Assertions.assertFalse(maze.playerWon());

        maze.getPellets().clear();
        Assertions.assertEquals(maze.getPellets().size(), 0);
        Assertions.assertTrue(maze.playerWon());
    }

    @Test
    public void checkGhostEaten()
    {
        maze.addScaredGhost(new ScaredGhost(new Position(1, 1), new South()));
        maze.setPacman(new PacMan(new Position(1,1), new South()));

        Assertions.assertNotEquals(maze.getScaredGhosts().size(), 0);
        Assertions.assertTrue(maze.wasGhostEaten());
    }

    @Test
    public void checkGhostRegen()
    {
        maze.addScaredGhost(new ScaredGhost(new Position(1, 1), new South()));
        Assertions.assertNotEquals(maze.getScaredGhosts().size(), 0);

        maze.setPacman(new PacMan(new Position(1,1), new South()));
        maze.ghostRegen();

        Assertions.assertEquals(new Position(14,14), maze.getHostileGhosts().get(0).getPosition());

    }
    @Test
    public void checkGateFunctionality(){

        PacMan pMan = maze.getPacman();
        maze.getGates().add(new Gate( new Position(27, 14)));
        Assertions.assertNotEquals(maze.getGates().size(), 0);

        /** There is a gate in this position**/
        Position posBefore = new Position(27, 14);

        /** Places the pacman in the gate **/
        pMan.setPosition(posBefore);

        /** Running the method **/
        maze.processGate();

        /** Checking if it worked as intended **/
        Position posAfter = new Position(1, 14);
        Assertions.assertEquals(maze.getPacman().getPosition(), posAfter);

    }

    @Test
    public void checkIfPacmanCanMove(){
        maze.setPacman(new PacMan(new Position(14, 23), new South()));
        maze.addWall(new Wall( new Position(0, 10)));
        Assertions.assertNotEquals(maze.getWalls().size(), 0);

        /** Pacman can move here because it´s empty **/
        Assertions.assertTrue(maze.pacmanCanMove(new Position(24, 10)));

        /** Pacman can´t move here because there is a wall in this position **/
        Assertions.assertFalse(maze.pacmanCanMove(new Position(0, 10)));
    }

    @Test
    public void checkPacManEaten(){
        maze.setPacman(new PacMan( new Position(3, 12), new North()));
        maze.addHostileGhost(new HostileGhost(new Position(3, 12), new North()));

        Assertions.assertNotEquals(maze.getHostileGhosts().size(), 0);
        Assertions.assertTrue(maze.wasPacmanEaten());
    }

    @Test
    public void moveHostileGhost(){
        maze.setPacman(new PacMan(new Position(14, 23), new South()));
        maze.addHostileGhost(new HostileGhost(new Position(1, 1), new North()));
        HostileGhost hostileGhost = maze.getHostileGhosts().get(maze.getHostileGhosts().size() - 1);
        Position beforePosition = hostileGhost.getPosition();
        maze.moveHostileGhosts();

        Assertions.assertEquals(hostileGhost.getDirection().toString(), "S");
        Assertions.assertEquals(hostileGhost.getPosition().getY(), beforePosition.getY() + 1);
    }

    @Test
    public void makeHostileGhostMove(){
        maze.setPacman(new PacMan(new Position(14, 23), new South()));
        maze.addHostileGhost(new HostileGhost(new Position(1, 1), new North()));
        HostileGhost hostileGhost = maze.getHostileGhosts().get(maze.getHostileGhosts().size() - 1);
        Position ghostPos = hostileGhost.getPosition();

        Assertions.assertTrue(maze.makeGhostMove(new Position(ghostPos.getX(), ghostPos.getY() + 1), hostileGhost, new South ()));
    }

    @Test
    public void checkReverseGhosts(){
        maze.addScaredGhost(new ScaredGhost(new Position(1,1), new South()));
        maze.reverseGhosts();
        Assertions.assertEquals(0, maze.getScaredGhosts().size());
    }

}