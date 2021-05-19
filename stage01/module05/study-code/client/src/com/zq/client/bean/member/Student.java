package com.zq.client.bean.member;

import java.util.ArrayList;

/**
 * @ClassName: Student
 * @Description: 学员的信息, 包括学员是否登录成功,学员的账号,学员的密码,以及学员最近三次的考试
 * 成绩
 * @Author: zq007
 * @Date: 2021/1/19 18:29
 * @Version: V1.0
 */
public class Student extends Member {

    private static final long serialVersionUID = 4560298817868330606L;
    private ArrayList<Integer> scores;

    public Student() {
    }

    public Student(String logIn, String account, String password,
                   ArrayList<Integer> scores) {
        super(account, password);
        this.scores = scores;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" + "account=" + getAccount() +
                " , password=" + getPassword() +
                " , lastThreeScore=" + scores +
                '}';
    }
}
