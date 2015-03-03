package com.jdk.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * User: Constantine Solovev
 * Created: 15.02.15  20:53
 */


public class RecursiveActionTask extends RecursiveAction {

    int start;
    int end;
    int[] data;
    int splitThreshold = 125;

    public RecursiveActionTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        if((end - start) <= splitThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = data[i] * data[i];
            }
        } else {
            int middle = (end + start) / 2;
            System.out.printf("fork (%d, %d) (%d, %d) \n", start, middle, middle, end);
            invokeAll(new RecursiveActionTask(data, start, middle), new RecursiveActionTask(data, middle, end));
        }

    }
}
