package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListSorterTest {
    private List<Integer> list;
    private List<Integer> expected;

    @BeforeEach
    private void helper(){
        list = Arrays.asList(3, 2, 6, 1, 4, 5, 7);
        expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    public void sort() {
        ListSorter sorter = new ListSorter();
        List<Integer> sorted = sorter.sort(list);

        Assertions.assertEquals(expected, sorted);
    }

    @Test
    //Comparing the stub result with the sort() method result
    public void distinct_bug_8726(){
        GenericListSorter mock = Mockito.mock(GenericListSorter.class);
        Mockito.when(mock.sort(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 2, 4));

        ListSorter sorter = new ListSorter();
        List<Integer> test = Arrays.asList(1, 2, 4, 2);

        Assertions.assertEquals(mock.sort(test), sorter.sort(test));
    }
}
