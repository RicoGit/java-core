package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  13:04
 */


public class BubbleSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (less(array[j + 1], array[j])) {
                    exchange(array, j, j + 1);
                }
            }
        }
    }
}
