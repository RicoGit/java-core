package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 29.02.16  21:40
 */


public class ShellSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        int n = array.length;
        int h = 1;

        // define h start value (h max value)
        while (h < n / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121 ...
        }

        // h-array sorting
        while(h >= 1) {
            for(int i = h; i < n; i++) {
                for(int j = i; j >= h && less(array[j], array[j -h]); j -= h) {
                    exchange(array, j, j -h);
                }
            }
            h = h / 3;
        }
    }
}
