package com.jdk.java8;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Project: Shamrock Web Portal.
 * User: Constantine Solovev
 * Date: 20.12.14
 * Time: 16:17
 */

public class FunctionExamples {

    public static void main(String[] args) {

        Function<String, String> capitalize = StringUtils::capitalize;

        Function<String, String> shuffleString = (param) -> {
            List<String> listChars = Arrays.asList(param.split(""));
            Collections.shuffle(listChars);
            String result = "";
            for (String listChar : listChars) {
                result += listChar;
            }
            return result;
        };

        Function<String, String> toBytes = (param) -> {
            String result = "";
            for (byte aByte : param.getBytes()) {
               result += " " + String.valueOf(aByte);
            }
            return result;
        };

        // вариант 1
        String result1 = capitalize.compose(shuffleString).apply("hello world");
        // тоже самое + toByte функция
        String result2 = shuffleString.andThen(capitalize).andThen(toBytes).apply("hello world");

        System.out.println(result1);
        System.out.println(result2);


    }


}
