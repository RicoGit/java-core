package com.sorting;

import java.util.Arrays;

/**
 * User: Constantine Solovev
 * Created: 28.02.16  11:48
 */


public class SorterTest {

    public static void test(Sorter sorter) {

        Comparable[] sourceArray =
            Arrays.stream(new int[]{1,2,3,5,4,3,9,8,7,34,0,67,0,456,7,9})
                .boxed()
                .toArray(Integer[]::new);


        Comparable[] expectedArray =
            Arrays.stream(new int[]{0,0,1,2,3,3,4,5,7,7,8,9,9,34,67,456})
                .boxed()
                .toArray(Integer[]::new);

        String sorterName = sorter.getClass().getSimpleName();

        sorter.sort(sourceArray);


        if (Arrays.equals(sourceArray, expectedArray)) {
            System.out.printf("    *** %-20s - Test success *** \n", sorterName);
        } else {
            System.out.printf("!!!!!! %-20s - Test failed !!!!! \n", sorterName);

            System.out.println("Expected " + Arrays.toString(expectedArray));
            System.out.println("Actually " + Arrays.toString(sourceArray));

        }

    }

}
