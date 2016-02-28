package com.jdk.io;

import java.io.File;
import java.io.IOException;

import com.jdk.io.file.NewReader;
import com.jdk.io.file.Reader;

/**
 * User: Solovev Constantine
 * Date: 15.11.14
 * Time: 18:06
 */

public class Main {

    public static void main(String[] args) {

        File file = new File("/home/rico/webdev/core/resources/test.file");

        try {
            // проверяем что файл существует, если нет создаем его
            if (file.exists() || !file.exists() && file.createNewFile()) {

                String result0 = Reader.readAsString(file);
                String result1 = NewReader.readAsString(file);
                System.out.println(result1);

            }
        } catch(IOException e) {
            System.out.printf("exception %s", e);
        }

    }

}