import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements OrderingStrategy{
  private List<StringDrink> drinks;
  private List<StringRecipe> recipes;

  public SmartStrategy(){
    this.drinks = new ArrayList<>();
    this.recipes = new ArrayList<>();
  }

  @Override
  public void wants(StringDrink drink, StringRecipe recipe, StringBar bar){
    if(bar.isHappyHour())
      bar.order(drink, recipe);

    else{
      drinks.add(drink);
      recipes.add(recipe);
    }
  }

  @Override
  public void happyHourStarted(StringBar bar){
    for(int i = 0; i < drinks.size(); i++)
      bar.order(drinks.get(i), recipes.get(i));

    for(int i = 0; i < drinks.size(); i++){
      drinks.remove(0);
      recipes.remove(0);
    }
  }

  @Override
  public void happyHourEnded(StringBar bar){

  }
}
