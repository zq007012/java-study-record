package com.lagou.zq.homework.code.test3;

/*
上网套餐类 特征：上网流量、每月资费
行为：显示所有套餐信息
 */
public class InternetPhonePackge extends PhonePackage implements InternetServiceInterface{
    private int internetTraffic;
    //private double charge;

    public InternetPhonePackge() {
    }

    public InternetPhonePackge(int internetTraffic, double charge) {
        super(charge);
        setInternetTraffic(internetTraffic);
    }

    public void show(){
        System.out.println("您的上网流量为: " + getInternetTraffic() + "GB" +
                " ; 您的上网资费每月为: " + getCharge() + "元");
    }

    public int getInternetTraffic() {
        return internetTraffic;
    }

    public void setInternetTraffic(int internetTraffic) {
        this.internetTraffic = internetTraffic;
    }

    @Override
    public void internetService(int internetTtraffic, PhoneCard phoneCard) {
        System.out.println("您的手机号为: " + phoneCard.getCardNumber() +
                " ; 您使用了" + internetTtraffic + "GB的上网流量");
    }

    /*public double getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }*/
}
