package com.polite.java8nt.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

//RecursiveTask为ForkJoinTask的抽象子类，有返回值的任务
public class SumTask extends RecursiveTask<Integer> {
    // 每个"小任务"最多 计算50个数
    private static final int MAX = 50;

    private int[] arr ;
    private int start;
    private int end;

    public SumTask(int[] arr , int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        System.out.println(" =============== 开始计算 ：");
        int sum = 0 ;
        if (end - start < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            System.out.println(Thread.currentThread().getName()+ "===== 任务分解 =======");
            int middle = (start + end) / 2;
            SumTask left = new SumTask(arr,start, middle);
            SumTask right = new SumTask(arr,middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
