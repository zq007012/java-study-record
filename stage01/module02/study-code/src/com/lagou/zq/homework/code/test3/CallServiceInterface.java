package com.lagou.zq.homework.code.test3;
/*
通话服务接口 抽象方法: 参数1: 通话分钟, 参数2: 手机卡类对象 让通话套餐类实现通话服务接口
 */
public interface CallServiceInterface {
    public abstract void callService(int callDuration , PhoneCard phoneCard);
}
