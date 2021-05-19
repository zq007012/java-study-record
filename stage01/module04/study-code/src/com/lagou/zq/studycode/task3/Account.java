package com.lagou.zq.studycode.task3;

public class Account implements Runnable {
    private int balance;

    public Account(int balance) throws Exception {
        setBalance(balance);
    }

    public Account() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) throws Exception {
        if (balance < 0){
            throw new Exception("account只能是大于等于0的整数");
        }
        this.balance = balance;
    }

    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        synchronized (this) {
            int temp = getBalance();
            System.out.println("账户中的钱正被线程" + id + "提取中...");
            temp -= 200;
            try {
                setBalance(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + id + "提了200块钱出来");
            System.out.println("账户中还剩下" + getBalance() + "元");
        }

    }
}
