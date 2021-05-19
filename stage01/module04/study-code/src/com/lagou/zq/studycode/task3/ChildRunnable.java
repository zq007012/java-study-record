package com.lagou.zq.studycode.task3;

import java.util.concurrent.locks.ReentrantLock;

public class ChildRunnable implements Runnable {
    private ReentrantLock lock;

    public ChildRunnable(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        //lock.lock();
        synchronized (this) {
            for (int i = 1; i < 51; i++){
                this.notifyAll();
                System.out.println("-----child-----" + i);
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //lock.unlock();
    }
}
