package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivisibleByFilterTest {
  private List<Integer> list1;
  private List<Integer> list2;

  @BeforeEach
  public void helper(){
    list1 = Arrays.asList(2, 10, 2764, 31236, 290188);
    list2 = Arrays.asList(5, 20, 125, 5500, 12340);
  }

  @Test
  public void acceptDivisibleBy2(){
    DivisibleByFilter filter = new DivisibleByFilter(2);
    for(Integer number : list1)
      Assertions.assertTrue(filter.accept(number));

    //Does the same parsing to the list2 which may have some numbers that are also divisible by 2
    List<Boolean> isDivisible = new ArrayList<>();
    for(Integer number : list2)
      isDivisible.add(filter.accept(number));

    Assertions.assertEquals(Arrays.asList(false, true, false, true, true), isDivisible);
  }

  @Test
  public void acceptDivisibleBy5(){
    DivisibleByFilter filter = new DivisibleByFilter(5);
    for(Integer number : list2)
      Assertions.assertTrue(filter.accept(number));

    //Does the same parsing to the list1 which may have some numbers that are also divisible by 3
    List<Boolean> isDivisible = new ArrayList<>();
    for(Integer number : list1)
      isDivisible.add(filter.accept(number));

    Assertions.assertEquals(Arrays.asList(false, true, false, false, false), isDivisible);
  }

}
