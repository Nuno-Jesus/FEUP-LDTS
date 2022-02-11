import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringInverterTest {
    @Test
    public void stringInverter() {
        StringDrink drink = new StringDrink("ABCD");
        StringInverter si = new StringInverter();
        si.execute(drink);
        Assertions.assertEquals("DCBA", drink.getText());
    }
}
