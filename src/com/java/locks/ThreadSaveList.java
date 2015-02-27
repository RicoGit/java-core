package com.java.locks;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  18:05
 */


public class ThreadSaveList<E> extends AbstractList<E> {

    List<E> innerStorageList = new ArrayList<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    @Override
    public E get(int index) {

        try {
            readLock.lock();
            Thread.sleep(5);
        } catch (InterruptedException ignored) { }

        E returnedValue = innerStorageList.get(index);
        readLock.unlock();
        return returnedValue;
    }

    @Override
    public boolean add(E e) {

        try {
            writeLock.lock();
            Thread.sleep(10);
        } catch (InterruptedException ignored) { }

        boolean result = innerStorageList.add(e);
        writeLock.unlock();
        return result;

    }

    @Override
    public int size() {
        readLock.lock();
        int size = innerStorageList.size();
        readLock.unlock();
        return size;
    }
}
