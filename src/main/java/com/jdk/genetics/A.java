package com.jdk.genetics;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 17:52
 */

public class A<T extends AbstractList & List & Collection> { //stupid, just for example

    T t;

    public void set(T t) {
        this.t = t;
        System.out.println("B set:" + t);
    }

    public T getT() {
        return t;
    }
}
