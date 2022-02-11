package pt.up.fe.ldts.example5;

public class East implements Direction{
  @Override
  public Direction rotateLeft(){
    return new North();
  }

  @Override
  public Direction rotateRight(){
    return new South();
  }

  @Override
  public void moveForward(Turtle turtle){
    turtle.setColumn(turtle.getColumn() + 1);
  }

  @Override
  public char getDirection(){
    return 'E';
  }

}
