package com.jdk.collection;

import java.util.ArrayDeque;

/**
 * User: Solovev Constantine
 * Date: 23.11.14
 * Time: 20:22
 */

public class Main {


    public static void main(String[] args) throws InterruptedException {

        ArrayDeque<String> que = new ArrayDeque<>();

        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        Producer producer3 = new Producer();

        int i = 0;
        while(i++ < 20) {
            String produce1 = producer1.produce();
            String produce2 = producer2.produce();
            String produce3 = producer3.produce();

            que.offer(produce1);
            que.offer(produce2);
            que.offer(produce3);

            if(que.peek() != null) {
                System.out.println("consume " + que.pop());
            }
            System.out.println(que.size() + " size");

            Thread.sleep(200);
        }


    }

}
