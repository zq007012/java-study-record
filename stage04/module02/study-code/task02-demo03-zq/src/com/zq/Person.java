package com.zq;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @ClassName: Person
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/13 10:39
 * @Version: V1.0
 */
public class Person implements HttpSessionBindingListener {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("对象" + ((Person)httpSessionBindingEvent.getValue()).toString() +  "绑定到session中了");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("对象" + ((Person)httpSessionBindingEvent.getValue()).toString() +  "与session解绑了");
    }
}
