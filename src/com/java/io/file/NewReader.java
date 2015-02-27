package com.java.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Project: Shamrock Web Portal.
 * User: Solovev Constantine
 * Date: 15.11.14
 * Time: 19:05
 */

public class NewReader {

    public static String readAsString(File file) {
        StringBuilder sb = new StringBuilder();

        try (FileInputStream fin = new FileInputStream(file)){

            int i;
            do {
                i = fin.read();
                sb.append((char) i);
            } while(i != -1);

            //удаляем последний символ -1
            sb.deleteCharAt(sb.length() - 1);

        } catch (IOException e) {
            System.out.println("Не могу найти или прочитать файл");
        }

        return sb.toString();
    }

}
