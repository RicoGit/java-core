package com.jdk.java8;

import java.util.*;

/**
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 18:06
 */

public class StreamsApi {

    public static void main(String[] args) {


        //1 - стримы не именяют исходную коллекцию
        //2 - стримы одноразовые

        /* Filter, forEach, unordered */

       String[] array = {"first", "second", "middle", "other", "last"};
       List<Integer> list = Arrays.asList(1,2,3,66,78,90,0,-2);

        Arrays.stream(array)
            // говорит о том то порядок выполнения нам не важен (возможны оптимизации производительности)
            .unordered()
             // для фильтрует элементы по предикату (Predicate)
            .filter(it -> it.startsWith("s"))
             // для каждого элемента вызывает функцию (Consumer)
            .forEach(System.out::println);

        /* sorted, map, mapTo*, reduce */

        OptionalInt reduce = Arrays.stream(array)
                // сортирует элеметны в натуральном порядке (эл. д.б. Comparable)
                .sorted()
                // меняет каждый элемент на другой
                .map(String::toUpperCase)
                // меняет тип каждого элемета на Integer
                .mapToInt(String::hashCode)
                // сворачивает стрим по заданной фунции
                .reduce((current, next) -> current + next );

        if (reduce.isPresent()) {
            System.out.println(reduce.getAsInt());
        }

        /* count, max, min */

        // все функции применяются аналогично
        Arrays.stream(array).max(String::compareTo).get();
        Arrays.stream(array).count();

        /* anyMatch, allMatch, noneMatch */

        list.stream()
                // объявляется параллельное вычисления
                .parallel()
                // находит любое вхождени по предикату, вернет boolean
                .anyMatch(it -> it == -2);






    }


}
