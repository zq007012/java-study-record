package com.lagou.zq.homework.code.test4.javabean;

import java.io.Serializable;

/*
其中 UserMessage 类的特征有：类型(字符串类型) 和 用户对象(User 类型)。
 */
public class UserMessage implements Serializable {
    private static final long serialVersionUID = 3034586880601825359L;
    /**
     * 只能取值"check"    "success"    "fail"
     */
    private String signInInfo;
    private User user;

    public UserMessage() {
    }

    public UserMessage(String signInInfo, User user) {
        setSignInInfo(signInInfo);
        setUser(user);
    }

    public String getSignInInfo() {
        return signInInfo;
    }

    public void setSignInInfo(String signInInfo) {
        this.signInInfo = signInInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "signIn='" + signInInfo + '\'' +
                ", user=" + user +
                '}';
    }
}
