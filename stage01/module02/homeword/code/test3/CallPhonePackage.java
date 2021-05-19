package com.lagou.zq.homework.code.test3;

/*
通话套餐类 特征：通话时长、短信条数、每月资费
行为: 显示所有套餐信息
 */
public class CallPhonePackage extends PhonePackage implements CallServiceInterface{
    private int callDuration;
    private int messageAmount;
    //private double charge;

    public CallPhonePackage(){}

    public CallPhonePackage(int callDuration, int messageAmount, double charge) {
        super(charge);
        setCallDuration(callDuration);
        setMessageAmount(messageAmount);
        //setCharge(charge);
    }
    public void show(){
        System.out.println("您的通话时长为" + getCallDuration() + "分钟" +
                " ; 您的短信数量为: " + getMessageAmount() + "条" +
                " ; 您的套餐资费是: " + getCharge() + "元");
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public int getMessageAmount() {
        return messageAmount;
    }

    public void setMessageAmount(int messageAmount) {
        this.messageAmount = messageAmount;
    }

    @Override
    public void callService(int callDuration, PhoneCard phoneCard) {
        System.out.println("您的手机号为: " + phoneCard.getCardNumber() +
                " ; 您使用了" + callDuration + "分钟的通话服务");
    }

    /*public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }*/
}
