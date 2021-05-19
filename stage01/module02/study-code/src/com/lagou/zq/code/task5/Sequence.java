package com.lagou.zq.code.task5;

public class Sequence {
    //成员变量
    public static String staticCnt = "staticCnt";
    private String cnt = "cnt";

    static{
        System.out.println("我是静态构造代码块");
        System.out.println("staticCnt = " + staticCnt);
        //System.out.println("cnt = " + cnt);//静态不能调用非静态
        System.out.println("------------------------------------------------------------------------");
    }

    {
        System.out.println("我是构造代码块");
        System.out.println("staticCnt = " + staticCnt);
        System.out.println("cnt = " + cnt);
        System.out.println("------------------------------------------------------------------------");

    }

    public Sequence(){
        System.out.println("我是构造方法");
        System.out.println("staticCnt = " + staticCnt);
        System.out.println("cnt = " + cnt);
    }

    public void show(){
        System.out.println("我只是一个普通的成员方法-------------------");
        System.out.println("son = " + staticCnt);
        System.out.println("son = " + cnt);
        System.out.println("我只是一个普通的成员方法-------------------");
    }

    public static void staticShow(){
        System.out.println("我是一个静态的成员方法");
    }
}
