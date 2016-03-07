package com.sorting.sorter;

import java.util.Arrays;
import java.util.Collections;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 02.03.16  22:13
 */


public class QuickSortWithInsertion implements Sorter {

    private int threshold = 32;

    @Override
    public void sort(Comparable[] array) {
        shuffleArray(array);
        sort(array, 0, array.length - 1);
    }

    /**
     * Recursive method.
     */
    private void sort(Comparable[] array, int start, int end) {

        if (start >= end) return;

        int mid = partition(array, start, end);

        if (threshold > mid - start) {
            intersectionSort(array, start, mid - 1);
        }

        if (threshold > end - mid) {
            intersectionSort(array, mid + 1, end);
        }

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

    private void intersectionSort(Comparable[] array, int start, int end) {

        for(int i = start + 1; i <= end; i++) {
            int indexToInsert = i;
            for (int j = i; j > start && less(array[i], array[j - 1]); j--) {
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
