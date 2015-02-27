package com.sorting;

import static java.lang.System.currentTimeMillis;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 27.02.15
 * Time: 9:55
 */

public class Bubble {


    public static void main(String[] args) {

        int[] intArray = ArraySource.getIntArray(10_000);


        System.out.println("start");
        long startTime = currentTimeMillis();
        simpleBubbleSort(intArray);
        System.out.printf("After sort %d elements, spent time %d ms", intArray.length, currentTimeMillis() - startTime);


    }

    private static void simpleBubbleSort(int[] intArray) {

        boolean permutation;
        do {
            permutation = false;
            for (int i = 0; i < intArray.length - 1; i++) {
                int i1 = intArray[i];
                int i2 = intArray[i + 1];
                if (i2 < i1) {
                    permutation = true;
                    intArray[i] = i2;
                    intArray[i+1]= i1;
                }
            }
        } while (permutation);

    }



}
