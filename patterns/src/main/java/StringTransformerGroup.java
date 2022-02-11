import java.util.List;

public class StringTransformerGroup implements StringTransformer{
    private List<StringTransformer> list;

    public StringTransformerGroup(List<StringTransformer> list){
        this.list = list;
    }

    @Override
    public void execute(StringDrink drink){
        for(StringTransformer t : list)
            t.execute(drink);
    }

    @Override
    public void undo(StringDrink drink){
        execute(drink);
    }
}
