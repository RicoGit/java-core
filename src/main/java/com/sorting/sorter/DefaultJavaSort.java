package com.sorting.sorter;

import java.util.Arrays;

import com.sorting.Sorter;

/**
 * User: Constantine Solovev
 * Created: 29.02.16  21:40
 */


public class DefaultJavaSort implements Sorter {

    @Override
    public void sort(Comparable[] array) {
        Arrays.sort(array);
    }
}
