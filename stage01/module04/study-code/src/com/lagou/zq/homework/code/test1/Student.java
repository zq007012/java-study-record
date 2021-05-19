package com.lagou.zq.homework.code.test1;

import com.lagou.zq.homework.code.test1.exception.AgeException;
import com.lagou.zq.homework.code.test1.exception.NumberException;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private static final long serialVersionUID = 9122873792976295942L;
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

    public Student(String name, int age, int number) throws AgeException, NumberException {
        setName(name);
        setAge(age);
        setNumber(number);
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

    /**
     * 设置学生的年龄
     * @param age
     * @throws AgeException 当输入的年龄不在3~30这个范围内就会抛出AgeException
     */
    public void setAge(int age) throws AgeException {
        if (age < 3 || age > 30){
            throw new AgeException("年龄不合理");
        }
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    /**
     * 设置学号
     * @param number
     * @throws NumberException 如果学号不在20200001 ~ 20209999这个范围内就会抛出NumberException
     */
    public void setNumber(int number) throws NumberException {
        if (number <20200001 || number > 20209999){
            throw new NumberException("学号不合理");
        }
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
