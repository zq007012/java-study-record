package com.lagou.zq.homework.code.test3;

/*
手机卡类 特征：卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量
行为：显示（卡号 + 用户名 + 当前余额）
 */
public class PhoneCard {
    private CardType cardType;
    private String cardNumber;
    private String user;
    private String password;
    private double acountBalance;
    private int callDuration;
    PhoneCard(){}

    public PhoneCard(String cardNumber) {
        setCardNumber(cardNumber);
    }

    public PhoneCard(String cardNumber, String user, double acountBalance) {
        setCardNumber(cardNumber);
        setUser(user);
        setAcountBalance(acountBalance);
    }

    //行为：显示（卡号 + 用户名 + 当前余额）
    public void show(){
        System.out.println("您的卡号是: " + getCardNumber() +" ; 您的用户名是: " + getUser() +
                " ; 您的当前余额是: " + getAcountBalance() + "元");
    }
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAcountBalance() {
        return acountBalance;
    }

    public void setAcountBalance(double acountBalance) {
        this.acountBalance = acountBalance;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }
}
