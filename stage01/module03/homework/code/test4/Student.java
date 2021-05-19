package com.lagou.zq.homework.code.test4;

import java.util.Objects;

public class Student {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 学号
     */
    private int number;

    public Student() {
    }

    public Student(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                number == student.number &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, number);
    }

    @Override
    public String toString() {
        return "Student{" +
                "姓名='" + name + '\'' +
                ", 年龄=" + age +
                ", 学号=" + number +
                '}';
    }
}
