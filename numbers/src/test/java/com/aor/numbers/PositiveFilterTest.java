package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PositiveFilterTest {
  private List<Integer> positives;
  private List<Integer> negatives;

  @BeforeEach
  public void helper(){
    positives = Arrays.asList(1, 2, 20, 40000, Integer.MAX_VALUE);
    negatives = Arrays.asList(0, -1, -2, -30, -65536, Integer.MIN_VALUE);
  }

  @Test
  //Accept numbers bigger than 0
  public void accept_positive(){
    PositiveFilter filter = new PositiveFilter();
    for(Integer number : positives)
      Assertions.assertTrue(filter.accept(number));
  }

  @Test
  //Reject numbers equal or below 0
  public void accept_negative(){
    PositiveFilter filter = new PositiveFilter();
    for(Integer number : negatives)
      Assertions.assertFalse(filter.accept(number));
  }
}
