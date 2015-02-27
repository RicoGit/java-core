package com.java.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * User: Constantine Solovev
 * Created: 11.02.15  10:11
 */


/**
 * 2 threads is working and main thread is await when threads work 5 times
 */
public class Main {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);
        new MyThreadLatch("latch thread 1", latch);
        new MyThreadLatch("latch thread 2", latch);

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        }

        System.out.println("done");

    }

}
