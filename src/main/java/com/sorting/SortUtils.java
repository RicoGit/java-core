package com.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.currentTimeMillis;

/**
 * User: Constantine Solovev
 * Date: 27.02.15
 */

public class SortUtils {

    public final static int DEFAULT_TIMES_OF_REPEAT = 1000;
    public final static int DEFAULT_ARRAY_LEGTH = 1000;


    public static List<BenchResult> runSortWithBenchMark(Sorter ... sorters) {
        return runSortWithBenchMark(DEFAULT_TIMES_OF_REPEAT, DEFAULT_ARRAY_LEGTH, sorters);
    }

    public static List<BenchResult> runSortWithBenchMark(int timesOfRepeats, int arrayLength, Sorter... sorters) {

        /* Preparing, create "timesOfRepeats" arrays with "arrayLength" length */

        List<Comparable[]> arrays = new ArrayList<>();
        for (int i = 0; i < timesOfRepeats; i++) {
            arrays.add(i, SortUtils.getIntArray(arrayLength));
        }

        return Stream.of(sorters).map(sorter -> {
            BenchResult result = new BenchResult(sorter.getClass().getSimpleName(), runBenchmark(sorter, arrays));
            System.out.println(String.format("Finish -> %s", result));
            return result;
        }).collect(Collectors.toList());

    }

    private static long runBenchmark(Sorter sorter, List<Comparable[]> sourceArray) {

        List<Comparable[]> deepCopy =
            sourceArray.stream()
                .map(array -> Arrays.copyOf(array, array.length))
                .collect(Collectors.toList());

        long startTime = currentTimeMillis();

        for (Comparable[] array : deepCopy) {
            sorter.sort(array);
        }

        long endTime = currentTimeMillis() - startTime;

        convincedOfResultsLoyalty(deepCopy, sorter.getClass().getSimpleName());

        return endTime;
    }

    /**
     * Ensure that all arrays are really sorted.
     */
    private static void convincedOfResultsLoyalty(List<Comparable[]> deepCopy, String sorterName) {
        deepCopy.stream()
            .forEach(array -> {
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i].compareTo(array[i + 1]) > 0) {
                        throw new RuntimeException(sorterName + " sort incorrectly");
                    };
                }
            });

    }

    private static Integer[] getIntArray(int length) {
        Random random = new Random();
        return IntStream.range(0, length)
                        .map(elem -> elem = random.nextInt(length))
                        .boxed()
                        .toArray(Integer[]::new);
    }


    public static class BenchResult implements Comparable<BenchResult> {
       public String sortName;
       public long sortResult;

        public BenchResult(String sortName, long sortResult) {
            this.sortName = sortName;
            this.sortResult = sortResult;
        }

        @Override
        public int compareTo(BenchResult o) {
            return Long.compare(this.sortResult, o.sortResult);
        }

        @Override
        public String toString() {
            return "   *** " + sortName + " *** => ( " + sortResult + " ) millis";
        }
    }

}
