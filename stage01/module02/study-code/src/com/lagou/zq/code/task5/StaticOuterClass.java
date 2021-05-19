package com.lagou.zq.code.task5;


public class StaticOuterClass {
    public String cnt = "Outer";
    public String outerCnt = "OuterCnt";
    private String priavateOuterCnt = "priavteOuer";
    private static String privateStaticOuterCnt = "privateStaticOuterCnt";
    public static String staticOuterCnt = "staticOuterCnt";
    public void show(){
        System.out.println("这是Outer的show方法---------------------------------");
        //调用静态内部类中的静态成员,就算是私有的静态成员也可以调用到
        //System.out.println("InnerClass.staticCnt = " + StaticOuterClass.StaticInnerClass.staticCnt);//StaticOuterClass.可以省略
        System.out.println("InnerClass.staticCnt = " + StaticInnerClass.staticInnerCnt);
        System.out.println("InnerClass.privateStaticCnt = " + StaticInnerClass.privateStaticCnt);

        //调用内部类中的非静态成员
        //StaticOuterClass.InnerClass staticInner = new StaticOuterClass.InnerClass();//这里的StaticOuterClass.可以省略
        StaticInnerClass staticInner = new StaticInnerClass();
        System.out.println("staticInner.cnt = " + staticInner.cnt);
        System.out.println("staticInner.innerCnt = " + staticInner.innerCnt);
        System.out.println("staticInner.privateCnt = " + staticInner.privateInnerCnt);
        System.out.println("这是Outer的show方法---------------------------------");
    }

    public static class StaticInnerClass {
        public String cnt = "Inner";
        public String innerCnt = "InnerCnt";
        private String privateInnerCnt = "privateInnerCnt";
        public static String staticInnerCnt = "staticInnerCnt";
        private static String privateStaticCnt = "staticCnt";

        public void show(){
            System.out.println("这是Inner的show方法-------------------------------------");
            System.out.println("innerCnt = " + innerCnt);
            System.out.println("privateInnerCnt = " + privateInnerCnt);

            //在静态内部类中非静态的成员可以访问静态成员
            System.out.println("staticInnerCnt = " + staticInnerCnt);
            System.out.println("privateStaticCnt = " + privateStaticCnt);

            //在静态内部类中只能访问到外部类的静态成员, 不过静态内部类的
            System.out.println("StaticOuterClass.staticOuterCnt = " + StaticOuterClass.staticOuterCnt);
            System.out.println("StaticOuterClass.staticOuterCnt = " + staticOuterCnt);//省略了StaticOuterClass.
            System.out.println("StaticOuterClass.privateStaticOuterCnt = " + StaticOuterClass.privateStaticOuterCnt);
            System.out.println("StaticOuterClass.privateStaticOuterCnt = " + privateStaticOuterCnt);//省略了StaticOuterClass.

            System.out.println("这是Inner的show方法-------------------------------------");

        }

    }
}
