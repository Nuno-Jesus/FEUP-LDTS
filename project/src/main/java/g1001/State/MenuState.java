package g1001.State;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import g1001.Buttons.Button;
import g1001.Buttons.ExitButton;
import g1001.Buttons.HighscoresButton;
import g1001.Buttons.PlayButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MenuState implements State{
  private List<Button> buttons;
  private int selected;

  public MenuState(){
    this.selected = 0;
    buttons = new ArrayList<>();

    buttons.add(new PlayButton(26, 13, new PlayState(null)));
    buttons.add(new HighscoresButton(26, 21,  new HighscoresState()));
    buttons.add(new ExitButton(26, 29,  new ExitState()));

    buttons.get(selected).setSelected(true);
  }

  @Override
  public State execute(Screen screen) throws InterruptedException, IOException {
    while (true) {
      TimeUnit.MILLISECONDS.sleep(10);

      draw(screen, 3, 15);
      KeyStroke key = screen.pollInput();

      State newState = processKey(key);
      if(newState != null)
        return newState;
    }
  }

  @Override
  public void draw(Screen screen, int row, int col) throws IOException{
    screen.clear();

    TextGraphics graphics = screen.newTextGraphics();
    graphics.enableModifiers(SGR.BOLD);

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row),"####################################################");
    graphics.putString(new TerminalPosition(col, row + 1),"#                                                  #");

    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFAA"));
    graphics.putString(new TerminalPosition(col, row + 2),"      ###### ###### ###### #    # ###### #    #     ");
    graphics.putString(new TerminalPosition(col, row + 3),"      #    # #    # #      ##  ## #    # ##   #     ");
    graphics.putString(new TerminalPosition(col, row + 4),"      ###### ###### #      # ## # ###### # ## #     ");
    graphics.putString(new TerminalPosition(col, row + 5),"      #      #    # #      #    # #    # #   ##     ");
    graphics.putString(new TerminalPosition(col, row + 6),"      #      #    # ###### #    # #    # #    #     ");

    graphics.setForegroundColor(TextColor.Factory.fromString("#1455f5"));
    graphics.putString(new TerminalPosition(col, row + 7),"#                                                  #");
    graphics.putString(new TerminalPosition(col, row + 8),"####################################################");

    for(int i = 0; i < buttons.size(); i++)
      buttons.get(i).draw(graphics);


    screen.refresh();
  }

  @Override
  public State processKey(KeyStroke key) throws IOException{
    if(key == null)
      return null;

    buttons.get(selected).setSelected(false);

    switch(key.getKeyType()){
      case ArrowUp -> {
        if(selected > 0)
          selected--;
      }

      case ArrowDown -> {
        if(selected < buttons.size() - 1)
          selected++;
      }

      case Enter -> {return buttons.get(selected).execute();}
      case EOF -> {return new ExitState();}
      default -> {}
    }

    buttons.get(selected).setSelected(true);

    return null;
  }
}
