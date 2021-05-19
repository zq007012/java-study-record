package com.lagou.zq.studycode.task1;

public class ThrowsTest {
    public static void main(String[] args) {
        try {
            divide(5,0);
        } catch (Exception e) {
            System.out.println("异常的处理措施");
        }
    }

    private static void divide(int dividend, int divisor) throws Exception{
        System.out.println("我是异常前的语句");
        int result = dividend / divisor;
        System.out.println("我是异常后的语句");

    }
}
