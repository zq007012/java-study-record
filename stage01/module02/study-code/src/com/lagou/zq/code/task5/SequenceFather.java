package com.lagou.zq.code.task5;

@EgAnnotation("哦呵呵")
public class SequenceFather {
    //成员变量
    private static String fatherStaticCnt = "fatherStaticCnt";
    private String fatherCnt = "fatherCnt";

    static{
        System.out.println("我是静态构造代码块");
        System.out.println("fatherStaticCnt = " + fatherStaticCnt);
        System.out.println("sonStaticCnt = "  + SequenceSon.sonStaticCnt);
        //System.out.println("cnt = " + cnt);//静态不能调用非静态

    }

    {
        System.out.println("我是father的构造代码块");
        System.out.println("fatherStaticCnt = " + fatherStaticCnt);
        System.out.println("fatherCnt = " + fatherCnt);
        show();
        Integer.valueOf(98);
    }

    public SequenceFather(){
        System.out.println("我是Father的无参构造方法");
        System.out.println("fatherStaticCnt = " + fatherStaticCnt);
        System.out.println("fatherCnt = " + fatherCnt);
        show();
    }

    public SequenceFather(String cnt){
        System.out.println("我是Father的有参构造方法");
        System.out.println("fatherStaticCnt = " + fatherStaticCnt);
        System.out.println("fatherCnt = " + fatherCnt);
        show();
    }

    public void show(){
        System.out.println("我只是一个普通的成员方法-------------------");
        System.out.println("fatherStaticCnt = " + fatherStaticCnt);
        System.out.println("fatherCnt = " + fatherCnt);
        System.out.println("我只是一个普通的成员方法-------------------");

    }

    public static void staticShow(){
        System.out.println("我是一个静态的成员方法");
    }
}
