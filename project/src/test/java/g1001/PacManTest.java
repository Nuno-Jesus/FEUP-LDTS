package g1001;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g1001.Direction.*;
import g1001.Game.Elements.Moving.PacMan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class PacManTest {

    private PacMan pacman;

    @BeforeEach
    public void setup(){ pacman = new PacMan(new Position(70, 70), new North()); }

    @Test
    public void checkPacManPosition(){
        Assertions.assertEquals(pacman.getPosition().getX(), 70);
        Assertions.assertEquals(pacman.getPosition().getY(), 70);
    }

    @Test
    public void checkIfDrawn(){
        try {
            TerminalSize terminalSize = new TerminalSize(160, 160);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            Screen screen = new TerminalScreen(terminal);

            TextGraphics graphics = screen.newTextGraphics();

            pacman.draw(graphics);

            Assertions.assertEquals(graphics.getCharacter(70, 70).getCharacterString(), "V");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @Test
    public void movePacManNorth(){

        Position beforePos = pacman.getPosition();

        North n = new North();
        pacman.setDirection(n);
        pacman.setPosition(pacman.move());

        Assertions.assertEquals(beforePos.getX(), pacman.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() - 1, pacman.getPosition().getY());

    }

    @Test
    public void movePacManSouth() {

        Position beforePos = pacman.getPosition();

        South s = new South();
        pacman.setDirection(s);
        pacman.setPosition(pacman.move());

        Assertions.assertEquals(beforePos.getX(), pacman.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() + 1, pacman.getPosition().getY());

    }

    @Test
    public void movePacManEast()  {

        Position beforePos = pacman.getPosition();

        East e = new East();
        pacman.setDirection(e);
        pacman.setPosition(pacman.move());

        Assertions.assertEquals(beforePos.getX() + 1, pacman.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), pacman.getPosition().getY());

    }

    @Test
    public void movePacManWest()  {

        Position beforePos = pacman.getPosition();

        West w = new West();
        pacman.setDirection(w);
        pacman.setPosition(pacman.move());

        Assertions.assertEquals(beforePos.getX() - 1, pacman.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), pacman.getPosition().getY());

    }


}
