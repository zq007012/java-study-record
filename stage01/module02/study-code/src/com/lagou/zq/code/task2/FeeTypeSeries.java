package com.lagou.zq.code.task2;

class FeeTypeSeries {
	public static void main(String[] args) {
		System.out.println(getFeeType(55));
	}

	public static int getFeeType(int n){
		/*if (1 == n) {
			return 1;
		}else if (2 == n) {
			return 1;
		}
		return getFeeTypeSeries(n - 1) + getFeeTypeSeries(n -2);*/

		int a = 1 ;
		int b = 1 ;
		if (1 == n || 2==n) {
			return a;
		}

		for (int i = 3; i <= n; i++) {
			b += a ;
			a = b - a;
		}
		return b;
	}
}
