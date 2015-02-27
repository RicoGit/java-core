package com.java.multithreading.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * User: Constantine Solovev
 * Created: 10.02.15  22:21
 */

public class Consumer extends Thread {

    private Q<Message> q;
    private String name;
    private Semaphore semaphore;

    public Consumer(Q<Message> q, Semaphore semaphore, String name) {
        this.q = q;
        this.semaphore = semaphore;
        this.name = name;
    }

    public void consume() throws InterruptedException {
        semaphore.acquire();
        if (q.isEmpty()) {
            System.out.println("Q is empty");
            semaphore.release();
            Thread.sleep(new Random().nextInt(2000));
            return;
        }
        System.out.println(name + q.pop().message);
        semaphore.release();
        Thread.sleep(new Random().nextInt(2000));

    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
                return;
            }
        }
    }
}
