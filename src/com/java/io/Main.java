package com.java.io;

import com.java.io.file.NewReader;
import com.java.io.file.Reader;

import java.io.File;
import java.io.IOException;

/**
 * Project: Shamrock Web Portal.
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