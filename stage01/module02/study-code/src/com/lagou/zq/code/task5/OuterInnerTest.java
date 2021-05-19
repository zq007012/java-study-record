package com.lagou.zq.code.task5;

public class OuterInnerTest {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.show();

        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.show();
    }
}
