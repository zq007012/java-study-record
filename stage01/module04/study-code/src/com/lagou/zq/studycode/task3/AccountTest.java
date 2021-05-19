package com.lagou.zq.studycode.task3;

public class AccountTest {
    public static void main(String[] args) {
        try {
            Account account = new Account(1000);
            Thread thread1 = new Thread(account);
            thread1.start();
            System.out.println();

            /*Thread thread2 = new Thread(account);
            thread2.start();
            thread2.join();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main线程结束了");
    }
}
