package g1001.Direction;


public class North implements Direction {
  @Override
  public Position move(Position position){
    return new Position(position.getX(), position.getY() - 1);
  }

  @Override
  public String toString() {
    return "N";
  }
}
