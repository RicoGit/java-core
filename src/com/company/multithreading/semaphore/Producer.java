package com.company.multithreading.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * User: Constantine Solovev
 * Created: 10.02.15  22:23
 */


public class Producer {

    private Random random = new Random();
    private Q<Message> q;
    private Semaphore semaphore;

    public Producer(Q<Message> q, Semaphore semaphore) {
        this.q = q;
        this.semaphore = semaphore;
    }

    public void put() throws InterruptedException {
        semaphore.acquire();
        q.push(new Message("msg № " + random.nextInt(100)));
        System.out.printf("message pushed [%d] \n", q.size());
        semaphore.release();
        Thread.sleep(new Random().nextInt(500));

    }

}
