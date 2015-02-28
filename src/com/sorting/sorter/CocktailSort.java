package com.sorting.sorter;

import java.util.function.Consumer;

/**
 * User: Constantine Solovev
 * Created: 28.02.15  13:30
 */


public class CocktailSort implements Consumer<int[]>{


    @Override
    public void accept(int[] array) {

        for (int i = 0; i < array.length - i; i++) {

            for (int j = i; j < array.length - i - 1; j++) {

                int i1 = array[j];
                int i2 = array[j + 1];
                if (i2 < i1) {
                    array[j] = i2;
                    array[j+1]= i1;
                }

            }

            for (int j = array.length - i - 1; j > 0; j--) {

                int i1 = array[j];
                int i2 = array[j - 1];
                if (i2 > i1) {
                    array[j] = i2;
                    array[j - 1]= i1;
                }

            }

        }

    }

}
