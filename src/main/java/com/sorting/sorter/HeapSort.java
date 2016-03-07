package com.sorting.sorter;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 07.03.16  10:48
 */


public class HeapSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {

        int n = array.length - 1;

        // prepare head
        for(int k = n / 2; k >= 0 / 2; k--) {
            sink(array, k, n);
        }

        while (n > 0) {
            exchange(array, 0, n--);
            sink(array, 0, n);
        }

    }

    private void sink(Comparable[] array, int k, int n) {

        while (2 * k + 1 <= n) {
            int tmp = 2 * k + 1;

            if (tmp < n && less(array[tmp], array[tmp + 1])) tmp++;

            if(!less(array[k], array[tmp])) break;

            exchange(array, k, tmp);
            k = tmp;
        }
    }

}
