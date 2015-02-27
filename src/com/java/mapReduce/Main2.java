package com.java.mapReduce;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  16:34
 */


// вывести элементы массива в порядке их частоты появления в массиве
// выполнить расчет параллельно (fork-join)
public class Main2 {

    public static void main(String[] args) {

        System.out.println("start");

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        int[] data = new int[6400];

        fillInRandom(data);

        RecursiveMapTask recursiveMapTask = new RecursiveMapTask(data, 0, data.length);

        Map<Integer, Integer> mappedData = forkJoinPool.invoke(recursiveMapTask);

        Map<Integer, Integer> sortedResult = sortMapByValue(mappedData);

        System.out.println(sortedResult);

    }

    private static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> map) {

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    private static void fillInRandom(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = new Random().nextInt(1000);
        }
    }

}
