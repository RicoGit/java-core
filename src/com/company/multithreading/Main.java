package com.company.multithreading;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread currentThread = Thread.currentThread();
//                String threadName = currentThread.getName();
//                System.out.printf("%s start \n", threadName );
//
//                while (true) {
//                    System.out.printf("%s thread working \n", threadName );
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        System.out.printf("%s stopped \n", threadName );
//                    }
//                }
//
//            }
//        }).start();

        new MyThread(" 1");
        new MyThread("  2");
        new MyThread("   3");

        System.out.println("End");

    }

}