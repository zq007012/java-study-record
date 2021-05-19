package com.lagou.zq.homework.code.test3;

/*
编写测试类使用多态格式分别调用上述方法，方法体中打印一句话进行功能模拟即可
 */
public class PhoneTest {
    public static void main(String[] args) {
        System.out.println("开始进行功能模拟...");
        PhoneCard phoneCard = new PhoneCard("18634788899");

        CallServiceInterface csi = new CallPhonePackage();
        csi.callService(25,phoneCard);

        InternetServiceInterface isi = new InternetPhonePackge();
        isi.internetService(1,phoneCard);

        PhonePackage phonePackage1 = new CallPhonePackage(500,300,50);
        phonePackage1.show();

        PhonePackage phonePackage2 = new InternetPhonePackge(50,50);
        phonePackage2.show();
    }
}
