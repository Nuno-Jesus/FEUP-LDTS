package com.aor.numbers;

public class DivisibleByFilter implements GenericListFilter{
  private final Integer divider;

  public DivisibleByFilter(Integer divider){
    this.divider = divider;
  }

  @Override
  public boolean accept(Integer number){
    return (number % divider == 0);
  }
}
