import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCaseChangerTest {
    @Test
    public void stringCaseChanger() {
        StringDrink drink = new StringDrink("aBcD");
        StringCaseChanger cc = new StringCaseChanger();
        cc.execute(drink);
        Assertions.assertEquals("AbCd", drink.getText());
    }
}
