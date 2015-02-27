package com.java.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  18:04
 */


public class Main2 {

    public static void main(String[] args) throws InterruptedException {

        ThreadSaveList<String> list = new ThreadSaveList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 5 thread write continuously
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                String threadName = Thread.currentThread().getName();
                for (int j = 0; j < 5; j++) {
                    String value = threadName + " - " + j;
                    System.out.println("writing... " + value);
                    list.add(value); // each add lasts 10ms

                }
            });
        }

        // 4 threads read
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("    Reading... " + list.size());
                for (String s : list) {
                    System.out.println("            Reading " + s); // each get lasts 5ms
                }
            });
            Thread.sleep(50); // next thread starts in 50ms
        }

        executorService.shutdown();
    }



}
