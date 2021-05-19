package com.lagou.zq.code.task2;

class FactorialTest {
	public static void main(String[] args) {
		System.out.println("99的阶乘是: " + getFactorial(10));
	}
	public static int getFactorial(int n){
		/*int result = 1;
		//递推
		for (int i = 1; i <= n ; i++) {
			result *= i;
		}
		return result;*/
		//递归
		if (1 == n) {
			return 1;
		}

		return n * getFactorial(n - 1);
	}
}
