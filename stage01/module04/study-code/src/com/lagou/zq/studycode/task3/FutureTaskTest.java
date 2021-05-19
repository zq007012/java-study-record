package com.lagou.zq.studycode.task3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            for (int i = 0; i < 30000000; i++) {
                Thread.yield();
            }
            System.out.println("子线程执行结束");
            return "Hello World";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Thread.yield();
        Thread.yield();
        Thread.yield();
        Thread.yield();
        Thread.yield();
        Thread.yield();
        System.out.println("啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        System.out.println(futureTask.cancel(true));
        if (!futureTask.cancel(true)) {
            System.out.println("isDone(): " + futureTask.isDone());
        }

    }
}
