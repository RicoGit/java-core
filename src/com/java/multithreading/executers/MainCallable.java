package com.java.multithreading.executers;

import java.util.concurrent.*;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  14:46
 */


public class MainCallable {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(500);
            return "task 1";
        });

        Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(1500);
            return "task 2";
        });

        try {

            System.out.println(future1.get());
            System.out.println(future2.get());

        } catch (InterruptedException e) {
            System.out.println("interrupt");
        } catch (ExecutionException e) {
            System.out.println("computation failed");
        } finally {
            System.out.println("end");
            executorService.shutdown();
        }

    }

}
