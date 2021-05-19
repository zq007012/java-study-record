package com.lagou.zq.code.task5;

public class ObjectInClass {
    private String cnt = "cnt";
    ObjectInClass(){

    }
    ObjectInClass(String cnt){
        this.cnt = cnt;
    }
    public void show(){
        ObjectInClass oic = new ObjectInClass();
        System.out.println("oic.cnt = " + oic.cnt);
        System.out.println("this.cnt = " + this.cnt);
    }
}
