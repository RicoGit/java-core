package com.jdk.java8;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 16:41
 */

public class SuppliersConsumersExamples {

    public static void main(String[] args) {

        /* пример Suppliers */
        //тоже самое что функция только не принимает ни каких аргументов

        String template1= "%s = this is a %s supplier";
        String template2= "%s = это %s поставщик";

        Supplier<String> s1 = () -> String.format(template1, "result", "1");
        Supplier<String> s2 = () -> String.format(template2, "результат", "2 ой");

        System.out.println(s1.get());
        System.out.println(s2.get());


        /* пример Consumers*/

        // распечатать
        Consumer<String> show = System.out::println;
        Consumer<String> save = (p) ->  {
            // записать в базу!!
        };

        show.andThen(save).accept("test string");



    }


}
