package com.sorting.sorter;

/**
 * User: Constantine Solovev
 * Created: 01.03.16  9:25
 */


public class MergeSortDesc extends MergeSort {

    public MergeSortDesc(int arrayLength) {
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

        if (start >= end) return;

        int mid = start + (end - start) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);

        merge(array, start, mid, end);

    }
}
