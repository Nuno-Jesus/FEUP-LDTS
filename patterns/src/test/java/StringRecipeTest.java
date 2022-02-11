import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringRecipeTest {
    @Test
    public void stringRecipe() {
        StringDrink drink = new StringDrink( "AbCd-aBcD");

        StringInverter si = new StringInverter();
        StringCaseChanger cc = new StringCaseChanger();
        StringReplacer sr = new StringReplacer('A', 'X');

        List<StringTransformer> transformers = new ArrayList<>();
        transformers.add(si);
        transformers.add(cc);
        transformers.add(sr);

        StringRecipe recipe = new StringRecipe(transformers);
        recipe.mix(drink);
        Assertions.assertEquals("dCbX-DcBa", drink.getText());
    }

    @Test
    public void transformUndo() {
        StringDrink drink = new StringDrink( "AbCd-aBcD");

        StringInverter si = new StringInverter();
        StringCaseChanger cc = new StringCaseChanger();
        StringReplacer sr = new StringReplacer('A', 'X');

        si.execute(drink);
        cc.execute(drink);
        sr.execute(drink);

        sr.undo(drink);
        Assertions.assertEquals("dCbA-DcBa", drink.getText());

        cc.undo(drink);
        Assertions.assertEquals("DcBa-dCbA", drink.getText());

        si.undo(drink);
        Assertions.assertEquals("AbCd-aBcD", drink.getText());
    }
}
