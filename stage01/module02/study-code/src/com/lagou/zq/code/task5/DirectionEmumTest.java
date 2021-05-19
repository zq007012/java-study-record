package com.lagou.zq.code.task5;

public class DirectionEmumTest {
    public static void main(String[] args) {
        DirectionEmum down = DirectionEmum.DOWN;
        System.out.println(down.toString());
        DirectionEmum up = Enum.valueOf(DirectionEmum.class,"UP");
        System.out.println(up.toString());
        System.out.println(up.getDirection());
        System.out.println(up.ordinal());
        System.out.println(down.ordinal());
        System.out.println(up.compareTo(DirectionEmum.RIGHT));
    }
}
