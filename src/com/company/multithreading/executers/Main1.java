package com.company.multithreading.executers;

import com.company.multithreading.countdownlatch.MyThreadLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: Constantine Solovev
 * Created: 14.02.15  18:28
 */


public class Main1 {

    public static void main(String[] args) {

        CountDownLatch latch1 = new CountDownLatch(3);
        CountDownLatch latch2 = new CountDownLatch(3);
        CountDownLatch latch3 = new CountDownLatch(3);
        CountDownLatch latch4 = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new MyThreadLatch("thread 1", latch1));
        executor.execute(new MyThreadLatch("thread 2", latch2));
        executor.execute(new MyThreadLatch("thread 3", latch3));
        executor.execute(new MyThreadLatch("thread 4", latch4));

        try {
            latch1.await();
            latch2.await();
            latch3.await();
            latch4.await();
        } catch (InterruptedException e) {
            System.out.println("Interrupt");
        } finally {
            System.out.println("end");
            executor.shutdown();
        }

    }

}
