package com.jdk.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: Solovev Constantine
 * Date: 15.11.14
 * Time: 19:05
 */

public class Reader {

    public static String readAsString(File file) {
        StringBuilder result = new StringBuilder();
        FileInputStream fin = null;

        try {

            fin = new FileInputStream(file);

            int i;
            do {
                i = fin.read();
                result.append((char) i);
            } while(i != -1);

            //удаляем последний символ -1
            result.deleteCharAt(result.length() - 1);

        } catch (IOException e) {
            System.out.println("Не могу найти или прочитать файл");
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException e) {
                System.out.println("Не могу закрыть файл");
            }
        }
        return result.toString();
    }
}
