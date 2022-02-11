import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringDrinkTest {
    @Test
    public void stringDrink() {
        StringDrink drink = new StringDrink("ABCD");
        Assertions.assertEquals("ABCD", drink.getText());
        drink.setText("DCBA");
        Assertions.assertEquals("DCBA", drink.getText());
    }
}
