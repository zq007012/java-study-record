package com.lagou.zq.studycode.task1;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) throws AgeException{
        setName(name);
        setAge(age);
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

    public void setAge(int age) throws AgeException {
        if (age < 0 || age > 130){
            throw new AgeException("年龄输入不合法");
        }else {
            this.age = age;
        }
    }
}
