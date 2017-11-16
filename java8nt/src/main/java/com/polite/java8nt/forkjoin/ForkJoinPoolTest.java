package com.polite.java8nt.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new PrintTask(0,200));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();

        ForkJoinPool forkJoinPool2 = new ForkJoinPool();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1 ;
        }
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool2.submit(new SumTask(arr, 0, arr.length));
        Integer integer = forkJoinTask.get();
        System.out.println("====== sum :"+integer);
        forkJoinPool2.shutdown();
    }
}
