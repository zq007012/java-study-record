package com.lagou.zq.code.task4;

public class Shape {
    private int x;
    private int y;
    public Shape(){

    }
    public Shape(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void show(){
        System.out.println("横坐标是: " + x + " ; 纵坐标是: " + y);
    }
}
