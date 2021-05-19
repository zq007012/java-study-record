package com.lagou.zq.code.task5;

public class OuterClass {
    public String cnt = "Outer";
    public String outerCnt = "OuterCnt";
    public void show(){
        System.out.println("这是Outer的show方法---------------------------------");
        //内部类就相当于外部类的一个成员方法,所以内部类的成员变量对外部类来说就是个局部变量,因此在外部类的方法中是调用不到内部类的成员变量的
        //但是内部类同时还具备类的功能,可以用来new对象,那么就可以用内部类来new出个对象
        //在外部类中,内部类对象可以直接调用到内部类的私有成员
        //OuterClass.InnerClass inner = this.new InnerClass();//这里的OuterClass.和this.都可以省略
        InnerClass inner = new InnerClass();
        System.out.println("inner.innerCnt = " + inner.innerCnt);
        System.out.println("inner.privateCnt = " + inner.privateCnt);
        System.out.println("这是Outer的show方法---------------------------------");
    }

    public class InnerClass{
        public String cnt = "Inner";
        public String innerCnt = "InnerCnt";
        private String privateCnt = "PrivateCnt";
        public void show(){
            System.out.println("这是Inner的show方法-------------------------------------");
            System.out.println("innerCnt = " + innerCnt);
            System.out.println("outerCnt = " + outerCnt);
            System.out.println("cnt = " + cnt);//相当于省略this.
            System.out.println("this.cnt = " + this.cnt);
            System.out.println("OuterClass.this.cnt = " + OuterClass.this.cnt);

            System.out.println("这是Inner的show方法-------------------------------------");

        }
    }
}
