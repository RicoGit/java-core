package com.sorting;

import java.util.Collections;
import java.util.List;

import com.sorting.SortUtils.BenchResult;
import com.sorting.sorter.BubbleSort;
import com.sorting.sorter.BubbleSortBad;
import com.sorting.sorter.CocktailSort;
import com.sorting.sorter.DefaultJavaSort;
import com.sorting.sorter.InsertionSort;
import com.sorting.sorter.InsertionSortBad;
import com.sorting.sorter.MergeSortAsc;
import com.sorting.sorter.MergeSortDesc;
import com.sorting.sorter.MergeSortDescWithInsertion;
import com.sorting.sorter.QuickSort;
import com.sorting.sorter.SelectionSort;
import com.sorting.sorter.ShellSort;

import static com.sorting.SortUtils.DEFAULT_ARRAY_LEGTH;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  12:59
 */


public class Runner {


    public static void main(String[] args) throws InterruptedException {

        BubbleSortBad bubbleSortBad = new BubbleSortBad();
        BubbleSort bubbleSort = new BubbleSort();
        CocktailSort cocktailSort = new CocktailSort();
        SelectionSort selectionSort = new SelectionSort();
        InsertionSortBad insertionSortBad = new InsertionSortBad();
        InsertionSort insertionSort = new InsertionSort();
        ShellSort shellSort = new ShellSort();
        MergeSortDesc mergeSortDesc = new MergeSortDesc(DEFAULT_ARRAY_LEGTH);
        MergeSortAsc mergeSortAsc = new MergeSortAsc(DEFAULT_ARRAY_LEGTH);
        MergeSortDescWithInsertion mergeSortDescWithInsertion = new MergeSortDescWithInsertion(DEFAULT_ARRAY_LEGTH);
        QuickSort quickSort = new QuickSort();

        DefaultJavaSort defaultJavaSort = new DefaultJavaSort();

        testAll(
            bubbleSortBad,
            bubbleSort,
            cocktailSort,
            selectionSort,
            insertionSortBad,
            insertionSort,
            shellSort,
            mergeSortDesc,
            mergeSortAsc,
            mergeSortDescWithInsertion,
            quickSort,

            defaultJavaSort
        );

        List<BenchResult> results = SortUtils.runSortWithBenchMark(
            bubbleSortBad,
            bubbleSort,
            cocktailSort,
            selectionSort,
            insertionSortBad,
            insertionSort,
            shellSort,
            mergeSortDesc,
            mergeSortAsc,
            mergeSortDescWithInsertion,
            quickSort,

            // java platform default sort
            defaultJavaSort
        );

        Collections.sort(results);

        showResults(results);

        Thread.sleep(10);
    }

    private static void showResults(List<BenchResult> results) {
        System.out.println("\n Sorting benchmark results \n");
        results.forEach(System.out::println);
    }

    private static void testAll(Sorter... sorters) {
        for(Sorter sorter : sorters) {
            SorterTest.test(sorter);
        }
    }


}
