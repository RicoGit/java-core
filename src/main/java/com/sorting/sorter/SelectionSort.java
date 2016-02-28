package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  11:29
 */


public class SelectionSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        for (int i = 0; i < array.length; i++) {

            int indexOfMinValue = i;
            Comparable min = array[i];

            for (int j = i + 1; j < array.length; j++) {
                Comparable elem = array[j];
                if (less(elem, min)) {
                    min = elem;
                    indexOfMinValue = j;
                }
            }
            exchange(array, i, indexOfMinValue);
        }
    }

}
