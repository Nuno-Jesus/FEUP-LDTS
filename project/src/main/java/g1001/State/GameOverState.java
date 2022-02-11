package g1001.State;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import g1001.Highscores.Highscores;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameOverState implements State{
  private Highscores scores;
  private String username;
  private TextGraphics graphics;
  private int points;

  public GameOverState(int points){
    scores = new Highscores();
    username = "";
    this.points = points;
  }

  @Override
  @SuppressWarnings("StringSplitter")
  public State execute(Screen screen) throws InterruptedException, IOException {
    scores.readHighscores();
    int col = 57;
    draw(screen, 15, 15);

    while (true) {
      TimeUnit.MILLISECONDS.sleep(10);
      KeyStroke key = screen.pollInput();

      if (key != null && key.getKeyType() != KeyType.Enter && key.getKeyType() == KeyType.Character){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(col,27), "" + key.getCharacter());
        username+=key.getCharacter();
        screen.refresh();

        col++;
      }

      State newState = processKey(key);
      if(newState != null){
        String [] aux = username.split(" ");
        if (aux[0].length() > 0) {
          scores.addEntry(aux[0], Long.valueOf(points));
          scores.writeHighscores();
        }
        return newState;
      }
    }
  }

  @Override
  public void draw(Screen screen, int row, int col) throws IOException{
    screen.clear();
    this.graphics = screen.newTextGraphics();

    graphics = screen.newTextGraphics();
    graphics.enableModifiers(SGR.BOLD);

    //TextGraphics graphics = screen.newTextGraphics();

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row),"####################################################");
    graphics.putString(new TerminalPosition(col, row + 1),"#                                                  #");

    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFAA"));
    graphics.putString(new TerminalPosition(col, row + 2),"  ##### ##### #   # #####  ##### #   # ##### #####  ");
    graphics.putString(new TerminalPosition(col, row + 3),"  #     #   # ## ## #      #   # #   # #     #   #  ");
    graphics.putString(new TerminalPosition(col, row + 4),"  # ### ##### # # # ###    #   # ## ## ###   #####  ");
    graphics.putString(new TerminalPosition(col, row + 5),"  #   # #   # #   # #      #   #  ###  #     # #    ");
    graphics.putString(new TerminalPosition(col, row + 6),"  ##### #   # #   # #####  #####   #   ##### #   #  ");
    graphics.putString(new TerminalPosition(col, row + 7),"                                                    ");

    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    graphics.putString(new TerminalPosition(col-1, row + 8),"          Press 'ENTER' to go back to the menu      ");

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row + 9),"#                                                  #");
    graphics.putString(new TerminalPosition(col, row + 10),"####################################################");

    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    graphics.putString(new TerminalPosition(col, row + 12), "Enter your username (Press 'ENTER' after): ");

    screen.refresh();
  }

  @Override
  public State processKey(KeyStroke key) throws IOException{
    if(key == null)
      return null;

    else if(key.getKeyType() == KeyType.Enter)
      return new MenuState();

    else if(key.getKeyType() == KeyType.EOF)
      return new ExitState();

    return null;
  }


}
