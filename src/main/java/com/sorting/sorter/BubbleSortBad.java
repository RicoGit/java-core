package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.16  11:04
 */

public class BubbleSortBad implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        boolean permutation;

        do {
            permutation = false;

            for (int i = 0; i < array.length - 1; i++) {
                if (less(array[i + 1], array[i])) {
                    permutation = true;
                    exchange(array, i, i+1);
                }
            }

        } while (permutation);
    }
}
