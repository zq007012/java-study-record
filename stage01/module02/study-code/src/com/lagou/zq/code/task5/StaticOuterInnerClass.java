package com.lagou.zq.code.task5;

public class StaticOuterInnerClass {
    public static void main(String[] args) {
        StaticOuterClass.StaticInnerClass staticInnerClass = new StaticOuterClass.StaticInnerClass();
        System.out.println(StaticOuterClass.StaticInnerClass.staticInnerCnt);
        staticInnerClass.show();
    }
}
