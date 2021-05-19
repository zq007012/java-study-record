package com.zq.client.bean.member;

/**
 * @ClassName: Manager
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/19 11:51
 * @Version: V1.0
 */
public class Manager extends Member {

    private static final long serialVersionUID = 3399013579881283156L;

    public Manager() {
    }

    public Manager(String logIn, String account, String password) {
        super(account, password);
    }
}
