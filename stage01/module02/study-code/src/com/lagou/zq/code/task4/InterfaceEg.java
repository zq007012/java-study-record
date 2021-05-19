package com.lagou.zq.code.task4;

public interface InterfaceEg {
    public static void staticShow(){
        System.out.println("我是InterfaceEg的staticShow方法");
    }
    /*private static void staticShow(){

    }

    public static void staticShow1(){
        staticShow();
    }

    public static void staticShow2(){
        staticShow();
    }*/

    private void show(){

    }

    public default void show1(){
        show();
    }

    public default void show2(){
        show();
    }

}
