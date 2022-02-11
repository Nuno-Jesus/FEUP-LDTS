package pt.up.fe.ldts.example5;

public class North implements Direction {
  @Override
  public Direction rotateLeft() {
    return new West();
  }

  @Override
  public Direction rotateRight() {
    return new East();
  }

  @Override
  public void moveForward(Turtle turtle) {
    turtle.setRow(turtle.getRow() - 1);
  }

  @Override
  public char getDirection(){
    return 'N';
  }

}
