package pt.up.fe.ldts.example1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderLine> lines;

    public Order() {
        lines = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        lines.add(new OrderLine(product, quantity));
    }

    private double totalOrderPrice(){
        double total = 0.0;

        for (OrderLine line : lines)
            total += line.linePrice();

        return total;
    }

    public boolean isElegibleForFreeDelivery() {
        return totalOrderPrice() > 100.0;
    }

    public String printOrder() {
        StringBuilder printBuilder = new StringBuilder();

        for (OrderLine line : lines)
            printBuilder.append(line.toString() + "\n");

        printBuilder.append("Total: " + totalOrderPrice());
        return printBuilder.toString();
    }
}