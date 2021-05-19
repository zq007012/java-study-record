package com.lagou.zq.code.task4;

public class Rect extends Shape {
    private int length;
    private int width;

    public Rect() {
    }

    public Rect(int x, int y, int length, int width) {
        super(x, y);
        setLength(length);
        setWidth(width);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if ( length > 0){
            this.length = length;
        }else{
            System.out.println("长度不合理");
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if ( width > 0){
            this.width = width;
        }else{
            System.out.println("宽度不合理");
        }
    }

    @Override
    public void show() {
        super.show();
        System.out.println("长度是: " + length + " ; 宽度是: " + width);
    }

    public void sonShow(){

    }
}
