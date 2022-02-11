public class StringCaseChanger implements StringTransformer{
    @Override
    public void execute(StringDrink drink){
        String result = "";
        for(int i = 0; i < drink.getText().length(); i++){
            if(Character.isLowerCase(drink.getText().charAt(i)))
                result += Character.toUpperCase(drink.getText().charAt(i));

            else
                result += Character.toLowerCase(drink.getText().charAt(i));
        }

        drink.setText(result);
    }

    @Override
    public void undo(StringDrink drink){
        execute(drink);
    }
}
