package com.sorting;

import com.sorting.sorter.BubbleSort;
import com.sorting.sorter.BubbleSortBad;
import com.sorting.sorter.CocktailSort;
import com.sorting.sorter.DefaultJavaSort;
import com.sorting.sorter.InsertionSort;
import com.sorting.sorter.InsertionSortBad;
import com.sorting.sorter.SelectionSort;
import com.sorting.sorter.ShellSort;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  12:59
 */


public class Runner {


    public static void main(String[] args) {

        BubbleSortBad bubbleSortBad = new BubbleSortBad();
        BubbleSort bubbleSort = new BubbleSort();
        CocktailSort cocktailSort = new CocktailSort();
        SelectionSort selectionSort = new SelectionSort();
        InsertionSortBad insertionSortBad = new InsertionSortBad();
        InsertionSort insertionSort = new InsertionSort();
        ShellSort shellSort = new ShellSort();
        DefaultJavaSort defaultJavaSort = new DefaultJavaSort();

        testAll(bubbleSortBad, bubbleSort, cocktailSort, selectionSort, insertionSortBad, insertionSort, shellSort);

        SortUtils.runSortWithBenchMark(bubbleSortBad);

        SortUtils.runSortWithBenchMark(bubbleSort);

        SortUtils.runSortWithBenchMark(cocktailSort);

        SortUtils.runSortWithBenchMark(selectionSort);

        SortUtils.runSortWithBenchMark(insertionSortBad);

        SortUtils.runSortWithBenchMark(insertionSort);

        SortUtils.runSortWithBenchMark(shellSort);

        // java platform default sort
        SortUtils.runSortWithBenchMark(defaultJavaSort);


    }

    private static void testAll(Sorter... sorters) {
        for(Sorter sorter : sorters) {
            SorterTest.test(sorter);
        }
    }


}
