package com.company.multithreading.exchanger;

import java.util.concurrent.Exchanger;

/**
 * User: Constantine Solovev
 * Created: 13.02.15  13:04
 */


public class Main {


    public static void main(String[] args) throws InterruptedException {

        Exchanger<String> stringExchanger = new Exchanger<>();

        Thread thread1 =  new Thread(new Runnable() {
            String str = "x";
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {

                    try {
                        System.out.println("thread 1 - " + str);
                        str = stringExchanger.exchange(str + "a");
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }

                }
            }
        });

        Thread thread2 =  new Thread(new Runnable() {
            String str = "y";
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {

                    try {
                        System.out.println("thread 2 - " + str);
                        str = stringExchanger.exchange(str + "b");
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }

                }
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


    }

}
