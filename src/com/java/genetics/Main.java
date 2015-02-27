package com.java.genetics;

import java.util.LinkedList;
import java.util.List;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 16.11.14
 * Time: 18:39
 */

public class Main {

    public static void main(String[] args) {

        B<Class> classB = new B<>();
        classB.setT(new B<C>());

        A<LinkedList<String>> a = new A<>();
        a.set(new LinkedList<>());
        List<String> t = a.getT();

        t.add("test string");
        System.out.println(t.get(0));

        Class<? extends A> aClass = a.getClass();
        System.out.println(aClass);

    }

}
