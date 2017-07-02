package com.jdk.multithreading.countdownlatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Constantine Solovev
 * Created: 01.07.17  16:39
 */


/**
 * Map wrapper with read and write locks.
 */
public class ReadWriteLockMain {
    public static void main(String[] args) {

        MyConcurrentMap<String, Integer> myConcurrentMap = new MyConcurrentMap<>(new HashMap<String, Integer>());

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<?> future1 = executorService.submit(() -> {
            while (true) {
                Thread.sleep(200);
                Integer result = myConcurrentMap.get("key1");
                System.out.println("Thread 1 get result " + result);
            }
        });

        Future<String> future2 = executorService.submit(() -> {
            while (true) {
                Thread.sleep(1000);
                Integer result = myConcurrentMap.put("key1", new Random().nextInt(100));
                System.out.println("Thread 2 put key1 " + result);
            }
        });


        try {
            System.out.println(future1.get(4, TimeUnit.SECONDS));
            System.out.println(future2.get(6, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        } catch (ExecutionException e) {
            System.out.println("computation failed");
        } catch (TimeoutException e) {
            System.out.println("timeout");
        } finally {
            future1.cancel(true);
            future2.cancel(true);
            System.out.println("end");
            executorService.shutdown();
        }


    }


    static class MyConcurrentMap<K, V> {

        private final Map<K, V> originMap;

        private ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private Lock rLock = rwLock.readLock();
        private Lock wLock = rwLock.writeLock();

        public MyConcurrentMap(Map<K, V> originMap) {
            this.originMap = originMap;
        }

        public V get(K key) {
            rLock.lock();
            try {
                return originMap.get(key);
            } finally {
                rLock.unlock();
            }
        }

        public V put(K key, V value) {
            wLock.lock();
            try {
                return originMap.put(key, value);
            } finally {
                wLock.unlock();
            }
        }

    }

}
