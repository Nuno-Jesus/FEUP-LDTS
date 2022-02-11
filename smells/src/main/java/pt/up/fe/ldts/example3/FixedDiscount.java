package pt.up.fe.ldts.example3;

public class FixedDiscount implements Discount{
  private int discount;

  public FixedDiscount(int discount){
    this.discount = discount;
  }

  @Override
  public double applyDiscount(double price) {
    return price - (double) discount;
  }
}
