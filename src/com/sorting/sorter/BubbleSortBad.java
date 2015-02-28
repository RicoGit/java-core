package com.sorting.sorter;

import java.util.function.Consumer;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 27.02.15
 * Time: 9:55
 */

public class BubbleSortBad implements Consumer<int[]>{

    @Override
    public void accept(int[] array) {

        boolean permutation;

        do {

            permutation = false;
            for (int i = 0; i < array.length - 1; i++) {

                int i1 = array[i];
                int i2 = array[i + 1];
                if (i2 < i1) {
                    permutation = true;
                    array[i] = i2;
                    array[i+1]= i1;
                }
            }

        } while (permutation);
    }
}
