package com.jdk.genetics;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 17:50
 */

public interface GenericInterface < T, V extends A, X extends Number> {

    public void setAll(T t, V v, X x);

    public T getT();

    public V getV();

    public X getX();

    void set(T t);

    void set(V v);


}
