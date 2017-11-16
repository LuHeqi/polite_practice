package com.polite.java8nt.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

//RecursiveAction为ForkJoinTask的抽象子类，没有返回值的任务
public class PrintTask extends RecursiveAction {
    // 每个"小任务"最多只打印50个数
    private static final int MAX = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        if (end - start < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " 打印第" +i+ "页");
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();

        }
    }
}
