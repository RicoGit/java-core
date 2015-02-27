package com.java.locks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  17:50
 */


public class MainReentrantLock {


    public static void main(String[] args) {

        final ReentrantLock lock = new ReentrantLock();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

        for (int i = 0; i < 10; i++)
            executorService.execute(() -> {

                String threadName = Thread.currentThread().getName();
                try {

                    Thread.sleep(100);
                    lock.lock();
                    System.out.println(threadName + " work ");

                } catch (InterruptedException e) {
                    System.out.println("Interrupt");
                } finally {
                    lock.unlock();
                    System.out.println(threadName + " unlocked");
                }

            });


    }


}
