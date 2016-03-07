package com.sorting;

import java.util.Collections;
import java.util.List;

import com.sorting.SortUtils.BenchResult;
import com.sorting.sorter.BubbleSort;
import com.sorting.sorter.BubbleSortBad;
import com.sorting.sorter.CocktailSort;
import com.sorting.sorter.DefaultJavaSort;
import com.sorting.sorter.HeapSort;
import com.sorting.sorter.InsertionSort;
import com.sorting.sorter.InsertionSortBad;
import com.sorting.sorter.MergeSortAsc;
import com.sorting.sorter.MergeSortDesc;
import com.sorting.sorter.MergeSortDescWithInsertion;
import com.sorting.sorter.QuickSort;
import com.sorting.sorter.QuickSortWithInsertion;
import com.sorting.sorter.SelectionSort;
import com.sorting.sorter.ShellSort;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  12:59
 */


/**
 * todo
 * Tim sort
 * Radix sort
 */
public class Runner {


    public static void main(String[] args) throws InterruptedException {

        int arrayLength = 10_000;

        BubbleSortBad bubbleSortBad = new BubbleSortBad();
        BubbleSort bubbleSort = new BubbleSort();
        CocktailSort cocktailSort = new CocktailSort();
        SelectionSort selectionSort = new SelectionSort();
        InsertionSortBad insertionSortBad = new InsertionSortBad();
        InsertionSort insertionSort = new InsertionSort();
        ShellSort shellSort = new ShellSort();
        MergeSortDesc mergeSortDesc = new MergeSortDesc(arrayLength);
        MergeSortAsc mergeSortAsc = new MergeSortAsc(arrayLength);
        MergeSortDescWithInsertion mergeSortDescWithInsertion = new MergeSortDescWithInsertion(arrayLength);
        QuickSort quickSort = new QuickSort();
        QuickSortWithInsertion quickSortWithInsertion = new QuickSortWithInsertion();
        HeapSort heapSort = new HeapSort();

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
            quickSortWithInsertion,
            heapSort,

            defaultJavaSort
        );

        List<BenchResult> results = SortUtils.runSortWithBenchMark(100, arrayLength,
//            bubbleSortBad,
//            bubbleSort,
//            cocktailSort,
//            selectionSort,
//            insertionSortBad,
//            insertionSort,
//            shellSort,
            mergeSortDesc,
            mergeSortAsc,
            mergeSortDescWithInsertion,
            quickSort,
            quickSortWithInsertion,
            heapSort,

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
