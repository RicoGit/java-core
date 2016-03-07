package com.sorting.sorter;

/**
 * User: Constantine Solovev
 * Created: 01.03.16  9:25
 */


public class MergeSortDescWithInsertion extends MergeSort {

    private int threshold = 32;

    public MergeSortDescWithInsertion(int arrayLength) {
        super(arrayLength);
    }

    @Override
    public void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * Recursive method.
     */
    private void sort(Comparable[] array, int start,int end) {

        int mid = start + (end - start) / 2;

        if (threshold > mid - start) {
            // use insertionSort
            intersectionSort(array, start, mid);
            intersectionSort(array, mid + 1, end);
        } else {
            // continue to divide
            sort(array, start, mid);
            sort(array, mid + 1, end);
        }

        merge(array, start, mid, end);

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
