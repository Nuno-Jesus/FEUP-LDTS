package pt.up.fe.ldts.example3;

public class NullDiscount implements Discount{
  @Override
  public double applyDiscount(double price){
    return price;
  }
}
