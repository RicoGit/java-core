package com.company.java8;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 15:27
 */

public class Main {

    static int staticValue = 0;


    /* Интерфейс с дефолтным методом */

    interface Calculate {
        void simpleMethod(String str);
        default void defaultMethod(String str) {
            System.out.println("default method!");
        }
    }

    public static void main(String[] args) {


        /* лямбда  */

        //рекомендуется для функциональных интерфейсов использовать аннотацию @FunctionalInterface

        // полная версия
        final int value = 0;
        Converter c1 = (int number) -> {
//            value = 13;  // не компилируется нельзя менять значение внешних переменных
            staticValue = 13; // а вот статические переменные менять можно
            return String.valueOf(number + value + staticValue);
        };
        c1.convert(666);
        // внешние переменные используемые в лямбдах по сути финальные, хотя ставить final не обязательно

        // можно опускать типы для парраметров всегда и скобки и return для отднострочных реализаций
        Converter с2 = (number) -> String.valueOf(number);

        // супер сокращенная (через ссылку на метод)
        Converter с3 = String::valueOf;

        // !!!NOTE в внутри лябд нельзя вызывать дефолные методы интерфейсов !!!
//        Calculate calculate = (str) -> calculate.defaultMethod("test");     // ошибка компиляции

        /* ссылка на методы */

        // можно сылаться на любые статические методы, подходящие по сигнатуре, как на внутренние
        Divider d1 = Main::devide;

        // так и на методы внешних классов
        Divider d2 = DividerImpl::staticMethod;

        // а так же на нестатические методы экземпляров
        Divider d3 = new DividerImpl()::nonStaticMethod;

        /* ссылки на конструкторы */

        // метод create у фабрики будет вызывать подходящий конструктор класса Person
        PersonFactory pf = Person::new;
        Person person = pf.create("hello", "world");

        /* Добавленные функциональные интерфейсы */

        // см. PredicateExamples
        // см. FunctionExamples


    }


    public static String[] devide(String source) {
        String middle = String.valueOf(source.length() / 2);
        return source.split(middle);
    }

}
