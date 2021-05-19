package com.lagou.zq.homework.code.test3;
/*
上网服务接口 抽象方法: 参数1: 上网流量, 参数2: 手机卡类对象 让上网套餐类实现上网服务接口
 */
public interface InternetServiceInterface {
    public abstract void internetService(int internetTtraffic , PhoneCard phoneCard);
}
