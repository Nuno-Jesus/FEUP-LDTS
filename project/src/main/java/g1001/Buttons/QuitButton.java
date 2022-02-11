package g1001.Buttons;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.State.State;

public class QuitButton extends Button{
  public QuitButton(int col, int row, State state){
    super(col, row, state);
  }

  @Override
  public void draw(TextGraphics graphics) {
    if(isSelected)
      drawSelected(graphics);
    else{
      graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFAA"));
      graphics.putString(new TerminalPosition(col, row + 2),"       #### #  # #### ####      ");
      graphics.putString(new TerminalPosition(col, row + 3),"       ## # #  #  ##   ##       ");
      graphics.putString(new TerminalPosition(col, row + 4),"       # #  #  #  ##   ##       ");
      graphics.putString(new TerminalPosition(col, row + 5),"       ## # #### ####  ##       ");
    }
  }

  private void drawSelected(TextGraphics graphics){
    graphics.setForegroundColor(TextColor.Factory.fromString("#666600"));
    graphics.putString(new TerminalPosition(col, row + 2),"    ###### #    # ###### ######    ");
    graphics.putString(new TerminalPosition(col, row + 3),"    #    # #    #   ##     ##      ");
    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    graphics.putString(new TerminalPosition(col, row + 4),"          Go back to menu          ");
    graphics.setForegroundColor(TextColor.Factory.fromString("#666600"));
    graphics.putString(new TerminalPosition(col, row + 5),"    #   #  #    #   ##     ##      ");
    graphics.putString(new TerminalPosition(col, row + 6),"    #### # ###### ######   ##      ");
  }
}
