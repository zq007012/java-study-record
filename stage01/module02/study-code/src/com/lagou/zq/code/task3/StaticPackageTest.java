package com.lagou.zq.code.task3;

import static java.lang.Math.PI;//导入了Math类中的静态变量PI,现在这个类中可以直接通过变量名来调用PI了
import static java.lang.Math.*;//导入了Math类中的所有静态成员方法, 现在可以直接通过方法名来调用Math中的所有静态方法了
class StaticPackageTest {
	public static void main(String[] args) {
		System.out.println("圆周率Π = " + PI);
        System.out.println("16的平方根是:" + sqrt(16 * 1.0));
        
	}
}