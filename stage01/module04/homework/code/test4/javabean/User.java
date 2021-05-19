package com.lagou.zq.homework.code.test4.javabean;

import java.io.Serializable;

/*
其中 User 类的特征有：用户名、密码(字符串类型)
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5939887051090398732L;
    private String userName;
    private String password;

    public User() {

    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
