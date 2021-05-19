package com.lagou.zq.studycode.task3;

import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class DeamonTest {
    public static void main(String[] args) {
        Thread deamonThread = new Thread(() -> {
            System.out.println("我是第一层守护线程吗? ---" + Thread.currentThread().isDaemon());
            new Thread(() -> {
                System.out.println("我是第二层守护线程吗? ---" + Thread.currentThread().isDaemon());
            }).start();
        });
        /*deamonThread.setDaemon(true);
        deamonThread.start();*/
        try {
            deamonThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 30; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程 : " + i);
        }


    }
}
