package com.java.genetics;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 17:53
 */

public class B<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(B<? super C1> c) {
        System.out.println(c);
    }
}
