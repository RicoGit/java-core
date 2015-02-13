package com.company.multithreading.countdownlatch;

import com.company.multithreading.MyThread;

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

class MyThreadLatch extends MyThread {

    CountDownLatch latch;

    public MyThreadLatch(String threadName, CountDownLatch latch) {
        super(threadName);
        this.latch = latch;
    }


    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                latch.countDown();

                if (latch.getCount() == 0) {
                    return;
                }

                System.out.println(this.threadName + " working " + latch.getCount());

            } catch (InterruptedException e) {
                System.out.println("interrupt");
            }
        }
    }

}
