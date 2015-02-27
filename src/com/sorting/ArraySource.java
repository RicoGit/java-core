package com.sorting;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 27.02.15
 * Time: 9:57
 */

public class ArraySource {

    public static int[] getIntArray(int length) {
        Random random = new Random();
        return IntStream.range(1, length+1).map(elem -> elem = random.nextInt(length)).toArray();
    }

}
