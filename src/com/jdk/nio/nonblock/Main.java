package com.jdk.nio.nonblock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: Constantine Solovev
 * Created: 01.03.15  20:20
 */


public class Main {

    public static void main(String[] args) {

        /*
         * на входе есть 10 номеров статей с хабра
       + * 1 - скачать статьи в одном потоке не блокируюсь
       + * 2 - поместить их в все очеред
         * 3 - вторым потоком рабобрать содержимое (убрать системную информацию) записавать статьи в файл
         * 4 - дополнительно рассчиатать 30 самый встечающихся слов во все тестах
         * */

        ExecutorService executor = Executors.newFixedThreadPool(3);

        int storeCapacity = 5;
        Queue<Integer> postsArray = new LinkedList<>(Arrays.asList(253711, 253699, 253698, 253697, 253696, 253695, 253694, 253693, 253692, 253600));

        PageStore store = new PageStore(storeCapacity);

        HabrLittleReader habrLittleReader = new HabrLittleReader(store, postsArray);
        PageConsumer pageConsumer = new PageConsumer(store, postsArray.size());

        executor.execute(habrLittleReader::readAll);
        executor.execute(pageConsumer::process);

        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Program is finished");
        executor.shutdown();

    }
}
