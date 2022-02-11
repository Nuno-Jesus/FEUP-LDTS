package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class ListFiltererTest {
  private List<Integer> list1;
  private List<Integer> list2;

  @BeforeEach
  public void helper(){
    list1 = Arrays.asList(1, 40, 112124, Integer.MAX_VALUE, 12938, 349, 90);
    list2 = Arrays.asList(1, 41, 21, 1253, 3, 9, 1025);
  }

  @Test
  public void filterPositives(){
    class StubListPositiveFilter implements GenericListFilter{
      @Override
        public boolean accept(Integer number){
          return true;
      }
    }
    GenericListFilter filter = new StubListPositiveFilter();
    ListFilterer filterer = new ListFilterer(filter);

    //All elements are positive so the return should be an equal list
    Assertions.assertEquals(list1, filterer.filter(list1));
  }

  @Test
  public void filterDivisibleBy(){
    class StubListDivisibleByFilter implements GenericListFilter{
      @Override
      public boolean accept(Integer number){
        return false;
      }
    }

    GenericListFilter filter = new StubListDivisibleByFilter();
    ListFilterer filterer = new ListFilterer(filter);

    //The seconds list doesn't have any numbers divisible by 2, so the list won't have any elements
    Assertions.assertEquals(0, filterer.filter(list2).size());
  }
}
