package com.lagou.zq.studycode.task3;

import java.util.concurrent.locks.ReentrantLock;

public class MainTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ChildRunnable child = new ChildRunnable(lock);
        Thread thread = new Thread(child);
        thread.start();

        //lock.lock();
        synchronized (child) {
            for (int i = 1; i < 51; i++){
                child.notifyAll();
                System.out.println("=====main=====" + i);
                try {
                    child.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //lock.unlock();
    }
}
