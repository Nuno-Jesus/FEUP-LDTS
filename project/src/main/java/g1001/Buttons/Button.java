package g1001.Buttons;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.State.State;

public abstract class Button {
  protected boolean isSelected;
  private State state;
  protected int col, row;

  public Button(int col, int row, State state){
    this.state = state;
    this.col = col;
    this.row = row;
    isSelected = false;
  }

  public abstract void draw(TextGraphics graphics);

  public State execute(){
    return getState();
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
}
