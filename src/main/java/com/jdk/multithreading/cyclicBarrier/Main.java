package com.jdk.multithreading.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.jdk.multithreading.MyThread;

/**
 * User: Constantine Solovev
 * Created: 11.02.15  10:29
 */


public class Main {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(5, new BarrierAction());

        for (int i = 0; i < 10; i++) {
            new BarrierThread("Thread " + i, barrier);
        }

    }


}

class BarrierThread extends MyThread {

    CyclicBarrier barrier;

    public BarrierThread(String threadName, CyclicBarrier barrier) {
        super(threadName);
        this.barrier = barrier;
    }

    @Override
    protected void doSomething() {
        try {
            System.out.println("wait");
            barrier.await();  // wait util barrier isn't flushed
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e);
        }
    }
}

//start when barrier is full
class BarrierAction implements Runnable {
    @Override
    public void run() {
        System.out.println("flush!!!");
    }
}
