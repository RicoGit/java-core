package com.jdk.genetics;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 17:49
 */

public class GenericClass {

    public <T> T getT(T t){
        return t;
    }

    public static <T> T getTStatic(T t) {
        return t;
    }

}
