package pt.up.fe.ldts.example5;

public interface Direction {
  public Direction rotateLeft();
  public Direction rotateRight();
  public void moveForward(Turtle turtle);
  public char getDirection();
}
