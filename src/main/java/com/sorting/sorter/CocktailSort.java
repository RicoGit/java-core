package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  13:30
 */


public class CocktailSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        for (int i = 0; i < array.length - i; i++) {

            for (int j = i; j < array.length - i - 1; j++) {
                if (less(array[j + 1], array[j])) {
                    exchange(array, j, j + 1);
                }
            }

            for (int j = array.length - i - 1; j > 0; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                }
            }

        }

    }

}
