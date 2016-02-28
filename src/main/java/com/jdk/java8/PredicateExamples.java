package com.jdk.java8;

import java.util.function.Predicate;

/**
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 15:54
 */

public class PredicateExamples {



    public static void main(String[] args) {

        Integer source1 = 123;
        Integer source3 = 8;

        //нам нужн предикат который отпределит что число не отрицательно, делиться на 2 и не больше 100

        Predicate<Integer> myPredicate = (number) -> number > 0;

        boolean result1 = myPredicate
                .and((it) -> it % 2 == 0 )
                .and((it) -> it < 100)
                .test(source1);

        boolean result3 = myPredicate
                .and((it) -> it % 2 == 0)
                .and((it) -> it < 100)
                .test(source3);

        System.out.println(result1 + " " + result3);

        /* или */
        Predicate<String> start = (str) -> true;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> notEmpty = isEmpty.negate();

        Predicate<String> passwordToShort = (str) -> str.length() < 5;
        Predicate<String> passwordToLong = (str) -> str.length() > 20;

        String password = "12312dfsfd";

        // вариат 1
        passwordToLong.and(notEmpty).and(passwordToShort).test(password);
        // вариант 2
        start
                .and(notEmpty)
                .and(passwordToLong)
                .and(passwordToShort)
                .test(password);





    }
}
