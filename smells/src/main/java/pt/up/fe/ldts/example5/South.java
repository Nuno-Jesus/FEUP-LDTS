package pt.up.fe.ldts.example5;

public class South implements Direction{
  @Override
  public Direction rotateLeft(){
    return new East();
  }

  @Override
  public Direction rotateRight(){
    return new West();
  }

  @Override
  public void moveForward(Turtle turtle){
    turtle.setRow(turtle.getRow() + 1);
  }

  @Override
  public char getDirection(){
    return 'S';
  }

}
