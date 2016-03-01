package com.sorting.sorter;

/**
 * User: Constantine Solovev
 * Created: 01.03.16  9:25
 */


public class MergeSortAsc extends MergeSort {

    public MergeSortAsc(int arrayLength) {
        super(arrayLength);
    }

    @Override
    public void sort(Comparable[] array) {

        int n = array.length;

        for (int subArraySize = 1; subArraySize <= n; subArraySize = subArraySize + subArraySize) {
            for (int i = 0; i < n - subArraySize; i += subArraySize + subArraySize) {
                merge(array, i, (i + subArraySize - 1), Math.min(i + subArraySize + subArraySize -1, n - 1));
            }
        }
    }
}
