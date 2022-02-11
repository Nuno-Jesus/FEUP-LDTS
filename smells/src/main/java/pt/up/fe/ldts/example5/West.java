package pt.up.fe.ldts.example5;

public class West implements Direction{
  @Override
  public Direction rotateLeft(){
    return new South();
  }

  @Override
  public Direction rotateRight(){
    return new North();
  }

  @Override
  public void moveForward(Turtle turtle){
    turtle.setColumn(turtle.getColumn() - 1);
  }

  @Override
  public char getDirection(){
    return 'W';
  }

}
