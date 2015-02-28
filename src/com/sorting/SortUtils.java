package com.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 27.02.15
 * Time: 9:57
 */

public class SortUtils {


    public static void runSortWithBenchMark(Consumer<int[]> sorter) {

        int timesOfRepeats = 1000;
        int arrayLength = 1000;

        /* preparing */

        List<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < timesOfRepeats; i++) {
            arrays.add(i, SortUtils.getIntArray(arrayLength));
        }
        long startTime = currentTimeMillis();

        /* start */

        for (int[] array : arrays) {
            sorter.accept(array);
        }

        /* Show results */

        System.out.printf(
                "%s  - sort %d elements %d times, time spent ( %d ms )  \n",
                sorter.getClass().getSimpleName(),
                arrayLength,
                timesOfRepeats,
                currentTimeMillis() - startTime
        );

    }

    private static int[] getIntArray(int length) {
        Random random = new Random();
        return IntStream.range(1, length + 1).map(elem -> elem = random.nextInt(length)).toArray();
    }

    public static void test(Consumer<int[]> sorter) {

        int[] sourceArray = new int[]{1,2,3,5,4,3,9,8,7,34,0,67,0,456,7,9};
        int[] expectedArray = new int[]{0,0,1,2,3,3,4,5,7,7,8,9,9,34,67,456};

        System.out.println("Before sort " + Arrays.toString(sourceArray));

        sorter.accept(sourceArray);

        System.out.println("After sort " + Arrays.toString(sourceArray));

        if (Arrays.equals(sourceArray, expectedArray)) {
            System.out.println("    ***     test success");
        } else {
            System.out.println("!!!!!! test failed !!!!!");
        }

    }

}
