package com.jdk.multithreading.phaser;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadFactory;

/**
 * User: Constantine Solovev
 * Created: 13.02.15  13:33
 */


public class Main {

    public static void main(String[] args) {

        // register 3 parts
        Phaser phaser = new Phaser(1);

        ThreadFactory factory = Executors.defaultThreadFactory();
        Thread thread1 = factory.newThread(new PhasedThread("thread 1", phaser));
        Thread thread2 = factory.newThread(new PhasedThread("thread 2", phaser));
        Thread thread3 = factory.newThread(new PhasedThread("thread 3", phaser));

        thread1.start();
        thread2.start();
        thread3.start();

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase() - 1 + " phase complete");

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase() - 1 + " phase complete");

        int currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println(currentPhase + " phase complete");

        phaser.arriveAndDeregister();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }


    }

}

class PhasedThread implements Runnable {

    String name;
    Phaser phaser;

    PhasedThread(String name, Phaser phaser) {
        this.name = name;
        phaser.register();
        this.phaser = phaser;
    }


    @Override
    public void run() {

        try {

            System.out.println(name + " worked");
            phaser.arriveAndAwaitAdvance();
            Thread.sleep(200);

            System.out.println(name + " worked");
            phaser.arriveAndAwaitAdvance();
            Thread.sleep(200);

            System.out.println(name + " worked");
            phaser.arriveAndDeregister();
            Thread.sleep(200);


        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
