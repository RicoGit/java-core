package com.jdk.nio.nonblock;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: Constantine Solovev
 * Created: 21.03.15  19:06
 */


public class PageStore {

    private static final int DEFAULT_MAX_CAPACITY = 5;

    private final BlockingQueue<Page> store;
    private final Queue<Page> untreatedPage = new ConcurrentLinkedQueue<>();

    public PageStore() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public PageStore(int maxCapacity) {
        this.store = new ArrayBlockingQueue<>(maxCapacity);
    }

    /**
     * blocking, waiting if necessary for space to become available
     * @param page page
     */
    public void save(Page page) {
        try {
            store.put(page);
        } catch (InterruptedException e) {
            /* errors will be processed later */
            untreatedPage.add(page);
        }
    }

    /**
     * blocking,  waiting if necessary until an element becomes available
     * @return page
     */
    public Page take() {

        int failureRate = 10;

        if (store.isEmpty()) {
            // add all failure pages to store again
            untreatedPage.forEach(page -> store.add(untreatedPage.peek()));
        }

        while(true) {
            try {
                return store.take();
            } catch (InterruptedException e) {
                System.out.println("Houston, we have a problem");
                if (failureRate-- == 0) {
                    System.out.println("Houston, we have so much errors");
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public int getCapacity() {
        return store.remainingCapacity();
    }



}
