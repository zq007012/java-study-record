package com.lagou.zq.homework.code.test3;

/*
用户消费信息类
    特征：统计通话时长、统计上网流量、每月消费金额
 */
public class UserConsumptionInfo {
    private int countCallDuration;
    private int countInternetTraffic;
    private int monthlyConsumptionAcount;

    public UserConsumptionInfo() {
    }

    public UserConsumptionInfo(int countCallDuration, int countInternetTraffic, int monthlyConsumptionAcount) {
        setCountCallDuration(countCallDuration);
        setCountInternetTraffic(countInternetTraffic);
        setMonthlyConsumptionAcount(monthlyConsumptionAcount);
    }

    public int getCountCallDuration() {
        return countCallDuration;
    }

    public void setCountCallDuration(int countCallDuration) {
        this.countCallDuration = countCallDuration;
    }

    public int getCountInternetTraffic() {
        return countInternetTraffic;
    }

    public void setCountInternetTraffic(int countInternetTraffic) {
        this.countInternetTraffic = countInternetTraffic;
    }

    public int getMonthlyConsumptionAcount() {
        return monthlyConsumptionAcount;
    }

    public void setMonthlyConsumptionAcount(int monthlyConsumptionAcount) {
        this.monthlyConsumptionAcount = monthlyConsumptionAcount;
    }
}
