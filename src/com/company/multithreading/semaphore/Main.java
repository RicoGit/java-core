package com.company.multithreading.semaphore;

import java.util.concurrent.Semaphore;

/**
 * User: Constantine Solovev
 * Created: 10.02.15  22:12
 */


public class Main {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        Q<Message> messageQ = new Q<>();

        Thread producerTread = new Thread(() -> {

            Producer producer = new Producer(messageQ, semaphore);

            while (true) {
                try {
                    producer.put();
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                    return;
                }
            }

        });


        Consumer consumer1 = new Consumer(messageQ, semaphore, "c1 ");
        Consumer consumer2 = new Consumer(messageQ, semaphore, "c2 ");
        Consumer consumer3 = new Consumer(messageQ, semaphore, "c3 ");


        producerTread.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        producerTread.join();
        consumer1.join();
        consumer2.join();
        consumer3.join();

    }

}
