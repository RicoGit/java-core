package com.jdk.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  20:49
 */

public class MainSimple {

    public static void main(String[] args) {

        int[] data = new int[1000];

        fillInRandom(data);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveAction task = new RecursiveActionTask(data, 0, data.length);
        forkJoinPool.invoke(task);
        System.out.println(Arrays.toString(data));
    }

    private static void fillInRandom(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = new Random().nextInt(100);
        }
    }


}
