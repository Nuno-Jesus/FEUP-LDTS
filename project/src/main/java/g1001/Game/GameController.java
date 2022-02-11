package g1001.Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import g1001.State.State;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameController {
  private State state;
  private Screen screen;
  private int terminalCols, terminalRows;


  public GameController(State state, int terminalCols, int terminalRows) {
    try {
      this.terminalCols = terminalCols;
      this.terminalRows = terminalRows;
      this.state = state;

      AWTTerminalFontConfiguration config = loadCustomFont("src/main/resources/fonts/Aquarius-RegularMono.ttf");
      TerminalSize terminalSize = new TerminalSize(terminalCols, terminalRows);
      DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
      terminalFactory.setTerminalEmulatorFontConfiguration(config);
      terminalFactory.setForceAWTOverSwing(true);
      Terminal terminal = terminalFactory.createTerminal();

      screen = new TerminalScreen(terminal);
      screen.setCursorPosition(null);
      screen.startScreen();
      screen.doResizeIfNecessary();

    } catch (IOException | FontFormatException e) {
      System.out.println("An exception has occurred");
    }
  }

  public void run() throws IOException, InterruptedException {

    while(true){
        state = state.execute(screen);
    }

  }

  private AWTTerminalFontConfiguration loadCustomFont(String path) throws IOException, FontFormatException {
    File fontFile = new File(path);
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(font);

    Font loadedFont = font.deriveFont(Font.PLAIN, 16);
    return AWTTerminalFontConfiguration.newInstance(loadedFont);
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getTerminalCols() {
    return terminalCols;
  }

  public int getTerminalRows() {
    return terminalRows;
  }
}
