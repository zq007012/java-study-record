package com.lagou.zq.code.task1;

public class Son extends Father {
    /*Son(){
        //super();
        System.out.println("这是Son的构造方法");
        //System.out.println(super.name);
        super.show();

    }*/
    String name = "son";
    @Override
    void  show(){
        System.out.println("这是Son的show方法");
    }

}
