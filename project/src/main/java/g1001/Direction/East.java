package g1001.Direction;


public class East implements Direction {
  @Override
  public Position move(Position position){
    return new Position(position.getX() + 1, position.getY());
  }

  @Override
  public String toString() {
    return "E";
  }
}
