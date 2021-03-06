package com.jdk.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

import com.jdk.multithreading.MyThread;

/**
 * User: Constantine Solovev
 * Created: 14.02.15  18:32
 */


public class MyThreadLatch extends MyThread {

    CountDownLatch latch;

    public MyThreadLatch(String threadName, CountDownLatch latch) {
        super(threadName);
        this.latch = latch;
    }


    @Override
    public void run() {

        while (latch.getCount() != 0) {

            try {
                latch.countDown();
                System.out.println(this.threadName + " working " + latch.getCount());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrupt");
            }
        }
    }

}
