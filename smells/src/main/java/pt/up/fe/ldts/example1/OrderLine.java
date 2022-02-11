package pt.up.fe.ldts.example1;

public class OrderLine {
    private Product product;
    private int quantity;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double linePrice(){
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return getProduct().getName() + "(x" + getQuantity() + "): " + linePrice();
    }
}
