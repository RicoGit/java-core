package com.company.mapReduce;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  21:42
 */


public class RecursiveMapTask extends RecursiveTask<Map<Integer, Integer>>{

    int[] data;
    int start;
    int end;
    int threshold = 100;

    public RecursiveMapTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<Integer, Integer> compute() {

        int diff = end - start;
        if (diff <= threshold) {
            return map(data, start, end);
        } else {

            int mid = ( start + end ) / 2;

            System.out.printf("fork (%d, %d) - (%d, %d) \n", start, mid, mid, end);
            RecursiveMapTask task1 = new RecursiveMapTask(data, start, mid);
            RecursiveMapTask task2= new RecursiveMapTask(data, mid, end);

            task1.fork();
            task2.fork();

            return merge(task1.join(), task2.join());
        }

    }

    Map<Integer, Integer> map(int[] data, int start, int end ) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = start; i < end; i++) {
            int item = data[i];
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }

        return map;
    }

    Map<Integer, Integer> merge(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {

        map1.entrySet()
                .stream()
                .forEach((entry)-> {
                    Integer key = entry.getKey();
                     if (map2.containsKey(key)) {
                         map2.put(key, entry.getValue() + map2.get(key));
                     } else {
                         map2.put(key, entry.getValue());
                     }
                });

        return map2;
    }

}
