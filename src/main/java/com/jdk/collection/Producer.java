package com.jdk.collection;

import java.util.Random;

/**
 * User: Solovev Constantine
 * Date: 23.11.14
 * Time: 20:28
 */

public class Producer {

    private static int counter = 0;
    private int number;

    public Producer() {
        number = counter++;
    }

    public String produce() throws InterruptedException {
        Random random = new Random();
        char[] sequence = new char[5];
        for (int i = 0; i < 5; i++) {
            sequence[i] = (char) random.nextInt(256);
        }
        return new String(sequence) + "produced by " + number;
    }

}
