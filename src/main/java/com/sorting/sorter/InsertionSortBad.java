package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.16  15:30
 */


public class InsertionSortBad implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        for (int i = 1; i < array.length; i++) {

            for (int j = i; ( j > 0 && less(array[j], array[j - 1]) ); j--) {
                exchange(array, j, j - 1);
            }
        }
    }
}
