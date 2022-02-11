import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringReplacerTest {
    @Test
    public void stringReplacer() {
        StringDrink drink = new StringDrink("ABCDABCD");
        StringReplacer sr = new StringReplacer('A', 'X');
        sr.execute(drink);
        Assertions.assertEquals("XBCDXBCD", drink.getText());
    }
}
