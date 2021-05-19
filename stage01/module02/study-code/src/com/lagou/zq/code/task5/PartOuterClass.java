package com.lagou.zq.code.task5;

public class PartOuterClass {
    public void example(){
       final class PartInnerClass{
            private int cnt = 1;
        }
        PartInnerClass pic = new PartInnerClass();



    }
}
