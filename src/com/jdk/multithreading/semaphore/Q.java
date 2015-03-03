package com.jdk.multithreading.semaphore;

import java.util.LinkedList;

/**
 * User: Constantine Solovev
 * Created: 10.02.15  22:30
 */


public class Q<T> {

    LinkedList<T> storage = new LinkedList<>();

    void push(T t) {
        storage.push(t);
    }

    T pop() {
        return storage.pop();
    }

    boolean isEmpty() {
        return storage.isEmpty();
    }

    int size() {
        return storage.size();
    }
}
