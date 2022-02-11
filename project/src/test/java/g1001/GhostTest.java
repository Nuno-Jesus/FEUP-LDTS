package g1001;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g1001.Direction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import g1001.Game.Elements.Moving.Ghosts.HostileGhost;
import g1001.Game.Elements.Moving.Ghosts.ScaredGhost;

import java.io.IOException;


public class GhostTest {

    private HostileGhost hostileGhost;
    private ScaredGhost scaredGhost;

    @BeforeEach
    public void setup(){
        hostileGhost = new HostileGhost(new Position(70, 70), new North());
        scaredGhost = new ScaredGhost(new Position(40, 40), new North());}

    @Test
    public void checkGhostsPosition(){
        Assertions.assertEquals(hostileGhost.getPosition().getX(), 70);
        Assertions.assertEquals(hostileGhost.getPosition().getY(), 70);

        Assertions.assertEquals(scaredGhost.getPosition().getX(), 40);
        Assertions.assertEquals(scaredGhost.getPosition().getY(), 40);
    }

    @Test
    public void checkIfHostileDrawn(){
        try{
            TerminalSize terminalSize = new TerminalSize(160, 160);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            hostileGhost.draw(graphics);

            Assertions.assertEquals(graphics.getCharacter(70, 70).getCharacterString(), "M");

        }catch(IOException e){
            System.out.println("IO Exception");
        }
    }


    @Test
    public void checkIfScaredDrawn(){
        try{
            TerminalSize terminalSize = new TerminalSize(160, 160);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            scaredGhost.draw(graphics);

            Assertions.assertEquals(graphics.getCharacter(40, 40).getCharacterString(), "S");
        }catch(IOException e){
            System.out.println("IO Exception");
        }
    }

    @Test
    public void moveGhostsNorth(){

        North n = new North();
        Position beforePos;

        beforePos = new Position(hostileGhost.getPosition().getX(), hostileGhost.getPosition().getY());
        hostileGhost.setDirection(n);
        hostileGhost.setPosition(hostileGhost.move());

        Assertions.assertEquals(beforePos.getX(), hostileGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() - 1, hostileGhost.getPosition().getY());

        beforePos = new Position(scaredGhost.getPosition().getX(), scaredGhost.getPosition().getY());
        scaredGhost.setDirection(n);
        scaredGhost.setPosition(scaredGhost.move());

        Assertions.assertEquals(beforePos.getX(), scaredGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() - 1, scaredGhost.getPosition().getY());

    }

    @Test
    public void moveGhostsSouth(){

        South s = new South();
        Position beforePos;

        beforePos = new Position(hostileGhost.getPosition().getX(), hostileGhost.getPosition().getY());
        hostileGhost.setDirection(s);
        hostileGhost.setPosition(hostileGhost.move());

        Assertions.assertEquals(beforePos.getX(), hostileGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() + 1, hostileGhost.getPosition().getY());

        beforePos = new Position(scaredGhost.getPosition().getX(), scaredGhost.getPosition().getY());
        scaredGhost.setDirection(s);
        scaredGhost.setPosition(scaredGhost.move());

        Assertions.assertEquals(beforePos.getX(), scaredGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY() + 1, scaredGhost.getPosition().getY());

    }

    @Test
    public void moveGhostsEast(){

        East e = new East();
        Position beforePos;

        beforePos = new Position(hostileGhost.getPosition().getX(), hostileGhost.getPosition().getY());
        hostileGhost.setDirection(e);
        hostileGhost.setPosition(hostileGhost.move());

        Assertions.assertEquals(beforePos.getX() +1, hostileGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), hostileGhost.getPosition().getY());

        beforePos = new Position(scaredGhost.getPosition().getX(), scaredGhost.getPosition().getY());
        scaredGhost.setDirection(e);
        scaredGhost.setPosition(scaredGhost.move());

        Assertions.assertEquals(beforePos.getX() +1 , scaredGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), scaredGhost.getPosition().getY());

    }

    @Test
    public void moveGhostsWest(){

        West w = new West();
        Position beforePos;

        beforePos = new Position(hostileGhost.getPosition().getX(), hostileGhost.getPosition().getY());
        hostileGhost.setDirection(w);
        hostileGhost.setPosition(hostileGhost.move());

        Assertions.assertEquals(beforePos.getX() -1, hostileGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), hostileGhost.getPosition().getY());

        beforePos = new Position(scaredGhost.getPosition().getX(), scaredGhost.getPosition().getY());
        scaredGhost.setDirection(w);
        scaredGhost.setPosition(scaredGhost.move());

        Assertions.assertEquals(beforePos.getX() -1 , scaredGhost.getPosition().getX());
        Assertions.assertEquals(beforePos.getY(), scaredGhost.getPosition().getY());

    }


}



