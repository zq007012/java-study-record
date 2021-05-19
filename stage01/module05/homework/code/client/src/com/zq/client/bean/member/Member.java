package com.zq.client.bean.member;

import java.io.Serializable;

/**
 * @ClassName: Member
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/20 9:33
 * @Version: V1.0
 */
public class Member implements Serializable {

    private static final long serialVersionUID = -8448153709203622625L;

    private String account;
    private String password;

    public Member() {
    }

    public Member(String account, String password) {
        setAccount(account);
        setPassword(password);
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Member member = (Member) o;

        if (!account.equals(member.account)) {
            return false;
        }
        return password.equals(member.password);
    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    @Override
    public String toString() {
        return "Member{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
