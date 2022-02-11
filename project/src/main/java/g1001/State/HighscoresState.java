package g1001.State;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import g1001.Highscores.Highscores;
import g1001.Highscores.Winner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HighscoresState implements State {
  private Highscores scores;
  private Highscores newScores;

  public HighscoresState() {
    scores = new Highscores();
  }

  @Override
  public State execute(Screen screen) throws InterruptedException, IOException {
    scores.readHighscores();

    while (true) {
      TimeUnit.MILLISECONDS.sleep(10);

      draw(screen, 3, 15);
      KeyStroke key = screen.pollInput();

      State newState = processKey(key);
      if (newState != null)
        return newState;
    }
  }

  @Override
  public void draw(Screen screen, int row, int col) throws IOException {
    screen.clear();

    TextGraphics graphics = screen.newTextGraphics();
    graphics.enableModifiers(SGR.BOLD);

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row), "####################################################");
    graphics.putString(new TerminalPosition(col, row + 1), "#                                                  #");

    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFAA"));
    graphics.putString(new TerminalPosition(col, row + 2), "      ###### ###### ###### ###### ###### ######     ");
    graphics.putString(new TerminalPosition(col, row + 3), "      #      #      #    # #    # #      #          ");
    graphics.putString(new TerminalPosition(col, row + 4), "      ###### #      #    # ###### ####   ######     ");
    graphics.putString(new TerminalPosition(col, row + 5), "           # #      #    # #  ##  #           #     ");
    graphics.putString(new TerminalPosition(col, row + 6), "      ###### ###### ###### #   ## ###### ######     ");

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row + 7), "#                                                  #");
    graphics.putString(new TerminalPosition(col, row + 8), "####################################################");

    newScores = formatScores();
    newScores.showHighscores(graphics, col, row + 10);

    graphics.putString(new TerminalPosition(col, row + 35), "        Press ESCAPE to go back to the menu         ");
    screen.refresh();
  }

  @Override
  public State processKey(KeyStroke key) throws IOException {
    if (key == null)
      return null;

    else if (key.getKeyType() == KeyType.Escape)
      return new MenuState();

    else if (key.getKeyType() == KeyType.EOF)
      return new ExitState();

    return null;
  }

  public Highscores formatScores() throws IOException {
    Highscores formattedScores = new Highscores();

    String straux;

    for (Winner winner : scores.getWinners()) {
      straux = winner.getName();
      for (int i = 0; i < 10 - winner.getName().length(); i++)
        straux += " ";
      formattedScores.addEntry(straux, winner.getScore());
    }
    return formattedScores;
  }
}
