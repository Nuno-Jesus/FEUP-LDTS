import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
  /******************* CONSTRUCTOR ******************/

  public Monster(int x, int y){
    super(new Position(x, y));
  }

  /******************** METHODS *********************/

  public Position move(){
    Random random = new Random();
    Position pos = new Position(getPosition().getX(), getPosition().getY());

    //Moves right or left randomly
    if(random.nextInt(2) == 0)
      moveLeft(pos);
    else
      moveRight(pos);

    //Moves upwards or downwards, randomly as well
    if(random.nextInt(2) == 0)
      moveUp(pos);
    else
      moveDown(pos);

    return pos;
  }

  private void moveUp(Position pos){
    pos.setY(pos.getY() - 1);
  }

  private void moveDown(Position pos){
    pos.setY(pos.getY() + 1);
  }

  private void moveLeft(Position pos){
    pos.setX(pos.getX() - 1);
  }

  private void moveRight(Position pos){
    pos.setX(pos.getX() + 1);
  }

  public void draw(TextGraphics graphics){
    graphics.setForegroundColor(TextColor.Factory.fromString("#3dff71"));
    graphics.putString(new TerminalPosition(position.getX(), position.getY()),"M");
  }
}
