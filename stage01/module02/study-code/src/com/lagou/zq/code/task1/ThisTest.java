package com.lagou.zq.code.task1;

public class ThisTest {
    private String cnt = "cnt";
    {
        System.out.println("这是ThisTest的构造代码块");
    }
    public ThisTest(){
        System.out.println("这是ThisTest的无参构造方法");
    }
    public ThisTest(String cnt){
        this();
        System.out.println("这是ThisTest的有参构造器");
    }
}
