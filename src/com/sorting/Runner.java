package com.sorting;

import com.sorting.sorter.BubbleSort;
import com.sorting.sorter.BubbleSortBad;
import com.sorting.sorter.CocktailSort;
import com.sorting.sorter.SelectionSort;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  12:59
 */


public class Runner {


    public static void main(String[] args) {

        SortUtils.runSortWithBenchMark(new BubbleSortBad());

        SortUtils.runSortWithBenchMark(new BubbleSort());

        SortUtils.runSortWithBenchMark(new CocktailSort());

        SortUtils.runSortWithBenchMark(new SelectionSort());


    }


}
