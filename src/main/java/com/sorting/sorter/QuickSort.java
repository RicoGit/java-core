package com.sorting.sorter;

import java.util.Arrays;
import java.util.Collections;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 02.03.16  22:13
 */


public class QuickSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {
        shuffleArray(array);
        sort(array, 0, array.length - 1);
    }

    /**
     * Recursive method.
     */
    private void sort(Comparable[] array, int start, int end) {

        if (end <= start) return;

        int mid = partition(array, start, end);
        sort(array, start, mid - 1);
        sort(array, mid + 1, end);

    }

    private int partition(Comparable[] array, int start, int end) {

        int left = start;
        int right = end + 1;
        // select first elem as mid of array
        Comparable selected = array[start];

        while(true) {

            while(less(array[++left], selected)) { if (left == end) break; }
            while(less(selected, array[--right])) { if (right == start) break; }

            if (left >= right) break;

            exchange(array, left, right);
        }

        exchange(array, start, right);
        return right;
    }

    private void shuffleArray(Comparable[] array) {
        Collections.shuffle(Arrays.asList(array));
    }
}
