package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 01.03.16  8:49
 */


public abstract class MergeSort implements Sorter {

    protected Comparable[] tmpArray;

    public MergeSort(int arrayLength) {
       tmpArray = new Comparable[arrayLength];
    }


    public void merge(Comparable[] array, int start, int mid, int end) {

        // copy first part of array to tmp array
        // for(int i = start; i <= end; i++) {
        //      tmpArray[i] = array[i];
        // }

        // System.arraycopy() is more faster
        System.arraycopy(array, start, tmpArray, start, end + 1 - start);

        int i = start;
        int j = mid + 1;

        for(int k = start; k <= end; k++) {
            if (i > mid) {
                array[k] = tmpArray[j++];
            } else if (j > end) {
                array[k] = tmpArray[i++];
            } else if (less(tmpArray[i], tmpArray[j])){
                array[k] = tmpArray[i++];
            } else {
                array[k] = tmpArray[j++];
            }

        }

    }

}
