package com.company.mapReduce;

import java.util.*;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  15:57
 */


// вывести элементы массива в порядке их частоты появления в массиве
public class Main {


    public static void main(String[] args) {

        int[] data = {1,2,3,2,3,2,5,67,7,7,5,4,2,54,76,6,4,5,7,21,4,6,89,0,56,5,2,7,8,4,3};

        Map<Integer, Integer> mappedData = map(data);

        LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<>(mappedData.entrySet());

        Collections.sort(list, ( e1, e2 ) -> e2.getValue().compareTo(e1.getValue()) );

        System.out.println(list);

    }



    static Map<Integer, Integer> map(int[] data) {

        Map<Integer, Integer> mappedData = new TreeMap<>();

        for (int item : data) {

            if (mappedData.containsKey(item)) {
                mappedData.put(item, mappedData.get(item) + 1);
            } else {
                mappedData.put(item, 1);
            }
        }

        return mappedData;
    }

}
