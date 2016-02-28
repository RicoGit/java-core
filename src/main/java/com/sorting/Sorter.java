package com.sorting;

/**
 * User: Constantine Solovev
 * Created: 28.02.16  11:04
 */

/**
 * Base interface for implementation sorting algorithms.
 */
public interface Sorter {

    /**
     * Do sorting for current array.
     */
    void sort(Comparable[] array);

    /**
     * Default method-helper for comparing 2 values.
     * @return true if c1 < c2 and false otherwise.
     */
    default boolean less(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) < 0;
    }

    /**
     * Default method for exchanging 2 array values.
     */
    default void exchange(Comparable[] array, int i, int j) {
        Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
