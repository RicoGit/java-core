package com.java.genetics;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 17:52
 */

public class A<T extends AbstractList & List & Collection> {

    T t;

    public void set(T t) {
        this.t = t;
        System.out.println("B set:" + t);
    }

    public T getT() {
        return t;
    }
}
