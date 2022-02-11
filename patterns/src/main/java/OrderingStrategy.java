public interface OrderingStrategy {
  public void wants(StringDrink drink, StringRecipe recipe, StringBar bar);
  public void happyHourStarted(StringBar bar);
  public void happyHourEnded(StringBar bar);
}
