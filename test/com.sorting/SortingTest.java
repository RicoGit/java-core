package com.sorting;

import com.sorting.sorter.BubbleSort;
import com.sorting.sorter.BubbleSortBad;
import com.sorting.sorter.CocktailSort;
import com.sorting.sorter.SelectionSort;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  13:16
 */


public class SortingTest {

    public static void main(String[] args) {

        SortUtils.test(new BubbleSortBad());
        SortUtils.test(new BubbleSort());
        SortUtils.test(new SelectionSort());
        SortUtils.test(new CocktailSort());

    }

}
