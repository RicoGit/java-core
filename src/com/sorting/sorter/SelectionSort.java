package com.sorting.sorter;

import java.util.function.Consumer;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  11:29
 */


public class SelectionSort implements Consumer<int[]>{

    @Override
    public void accept(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int indexOfMinValue = 0;
            int min = Integer.MAX_VALUE;
            for (int j = i; j < array.length; j++) {
                int elem = array[j];
                if (elem < min) {
                    min = elem;
                    indexOfMinValue = j;
                }
            }
            removeElemAndShift(array, i, indexOfMinValue);
            array[i] = min;
        }
    }

    private static void removeElemAndShift(int[] array, int startIndex, int indexOfRemovedElem) {
        System.arraycopy(array, startIndex, array, startIndex + 1, indexOfRemovedElem - startIndex);
    }

}
