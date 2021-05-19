package com.lagou.zq.code.task5;

public class SequenceSon extends SequenceFather {
    //成员变量
    public static String sonStaticCnt = "sonStaticCnt";
    private String sonCnt = "sonCnt";

    static{
        System.out.println("我是Son的静态构造代码块");
        System.out.println("sonStaticCnt = " + sonStaticCnt);
        //System.out.println("cnt = " + cnt);//静态不能调用非静态

    }

    {
        System.out.println("我是Son的构造代码块");
        System.out.println("sonStaticCnt = " + sonStaticCnt);
        System.out.println("sonCnt = " + sonCnt);
    }

    public SequenceSon(){
        super("cnt");
        System.out.println("我是Son的构造方法");
        System.out.println("sonStaticCnt = " + sonStaticCnt);
        System.out.println("sonCnt = " + sonCnt);
    }

    public SequenceSon(String sonCnt){
        super("cnt");

        System.out.println("我是Son的构造方法");
        System.out.println("sonStaticCnt = " + sonStaticCnt);
        System.out.println("sonCnt = " + sonCnt);
    }

    @Override
    public void show(){
        System.out.println("我只是Son的一个普通的成员方法-------------------");
        System.out.println("sonStaticCnt = " + sonStaticCnt);
        System.out.println("sonCnt = " + sonCnt);
        System.out.println("我只是Son一个普通的成员方法-------------------");
    }

    public static void staticShow(){
        System.out.println("我是一个静态的成员方法");
    }
}
