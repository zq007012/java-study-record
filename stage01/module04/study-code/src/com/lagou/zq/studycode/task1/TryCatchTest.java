package com.lagou.zq.studycode.task1;

public class TryCatchTest {
    public static void main(String[] args) {
        //System.out.println(tryCatchTest(0, 10));
        System.out.println(tryCatchTest(1, 10));
    }

    public static String tryCatchTest(int divisor, int dividend) {
        System.out.println("这里是不会产生异常的语句1");
        try{
            System.out.println("Above: 我是try语句里的语句, 在异常语句的上面");
            int result = dividend / divisor;//可能产生异常的语句
            System.out.println("Below: 我是try语句里的语句, 在异常语句的下面");
            return "我是try语句里的return语句";//未发生异常时, 会执行try语句里的return语句, 此时还未轮到finally语句执行
        }catch (ArithmeticException e){
            return "我是catich语句里的一个return语句";//发生异常时,会执行catch语句里的return语句, 此时还未轮到finally语句执行
        }finally {
            System.out.println("我是finally语句里的语句");
        }
    }
}
