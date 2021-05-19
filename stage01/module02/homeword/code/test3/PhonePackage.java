package com.lagou.zq.homework.code.test3;

public abstract class PhonePackage {
    private double charge;

    public PhonePackage() {
    }

    public PhonePackage(double charge) {
        setCharge(charge);
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public abstract void show();
}
