package com.lagou.zq.studycode.task3;

public class ThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 65; i < 91 ; i++){
                System.out.println("子线程里的字符是" + (char)i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        /*for (int i = 0; i < 30 ; i++){
            System.out.println("main线程里的i值是" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        try {
            System.out.println("我是join方法之前的语句");
            thread.join(10000);
            System.out.println("我是join方法之前的语句");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完这句主线程就结束了");
    }
}
