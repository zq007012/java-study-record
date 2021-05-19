package com.lagou.zq.code.task4;

public class ShapeRectTest {
    public static void main(String[] args) {
        Shape s1 = new Shape(1 , 2);
        s1.show();

        System.out.println("-----------------------------------");

        Rect r1 = new Rect(3,4,5,6);
        r1.show();

        System.out.println("-----------------------------------");

        Shape sr = new Rect(7,8,9,10);
        System.out.println(sr instanceof Rect);


    }
}
