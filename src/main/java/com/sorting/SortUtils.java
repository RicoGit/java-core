package com.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

/**
 * User: Constantine Solovev
 * Date: 27.02.15
 */

public class SortUtils {

    public final static int DEFAULT_TIMES_OF_REPEAT = 1000;
    public final static int DEFAULT_ARRAY_LEGTH = 1000;


    public static void runSortWithBenchMark(Sorter sorter) {
        runSortWithBenchMark(sorter, DEFAULT_TIMES_OF_REPEAT, DEFAULT_ARRAY_LEGTH);
    }

    public static void runSortWithBenchMark(Sorter sorter, int timesOfRepeats, int arrayLength) {

        /* Preparing, create "timesOfRepeats" arrays with "arrayLength" length */

        List<Comparable[]> arrays = new ArrayList<>();
        for (int i = 0; i < timesOfRepeats; i++) {
            arrays.add(i, SortUtils.getIntArray(arrayLength));
        }
        long startTime = currentTimeMillis();

        /* Start sorting*/

        for (Comparable[] array : arrays) {
            sorter.sort(array);
        }

        /* Show results */

        System.out.printf(
            "%-20s  - sort %d elements %d times, time spent ( %d ms )  \n",
            sorter.getClass().getSimpleName(),
            arrayLength,
            timesOfRepeats,
            currentTimeMillis() - startTime
        );

    }

    private static Integer[] getIntArray(int length) {
        Random random = new Random();
        return IntStream.range(0, length)
                        .map(elem -> elem = random.nextInt(length))
                        .boxed()
                        .toArray(Integer[]::new);
    }

}
