package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

public class ListFilterer {
  private final GenericListFilter filter;

  public ListFilterer(GenericListFilter filter){
    this.filter = filter;
  }

  public List<Integer> filter(List<Integer> list){
    List<Integer> filtered = new ArrayList<>();

    for(Integer number : list){
      if(filter.accept(number))
        filtered.add(number);
    }

    return filtered;
  }
}
