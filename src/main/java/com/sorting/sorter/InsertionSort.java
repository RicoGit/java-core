package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.16  15:58
 */

public class InsertionSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        for (int i = 1; i < array.length; i++) {

            int indexToInsert = i;

            for (int j = i; ( j > 0 && less(array[i], array[j - 1]) ); j--) {
                indexToInsert = j - 1;
            }

            if (i != indexToInsert) {
                insert(array, indexToInsert, i);
            }
        }
    }

    private static void insert(Comparable[] array, int indexToInsert, int indexFromInsert) {
        Comparable valueToInsert = array[indexFromInsert];
        System.arraycopy(array, indexToInsert, array, indexToInsert + 1, indexFromInsert - indexToInsert);
        array[indexToInsert] = valueToInsert;

    }

}
