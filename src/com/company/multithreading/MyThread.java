package com.company.multithreading;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 12.11.14
 * Time: 21:45
 */

public class MyThread implements Runnable {

    private String threadName;

    public MyThread(String threadName) {
        this.threadName = threadName;
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {
        System.out.println(threadName + "start");

        while (true) {
            System.out.printf("%s thread working \n", threadName );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.printf("%s stopped \n", threadName );
            }
        }

    }
}
