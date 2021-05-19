class Test2 {
	/*
	2. 编程题 编程找出 1000 以内的所有完数并打印出来。 所谓完数就是一个数恰好等于它的因子之和，如：6=1＋2＋3
	思路:
		完数就是一个数恰好等于它的除它本身之外的因子之和
		所以1不可能是完数
		1. 循环2-1000的数字,判断每个数字是否是完数
		2. 如何判断这个数字是否是完数? 可以让这个数字除以所有小于它的正整数,没有余数的就是他的因子,如果因子之和等于这个数那么这个数就是完数了,那就把这个数打印出来

	*/
	public static void main(String[] args) {
		System.out.println("1-1000中的完数有: ");
		//循环2-1000的数字,不必有1,因为1的因子是它本身,不可能是完数
		//判断每个数字是否是完数
		int divisorSum = 0;
		for (int a = 2; a <= 1000; a++) {
			/*//每个循环都会用到,不如在循环外省声明,可以减少声明变量的次数
			int divisorSum = 0;
			*/
			//获得所有因子的和
			for (int b = 1; b < a; b++) {
				int remainder = a % b;
				if (remainder == 0) {
					divisorSum = b + divisorSum;
				}
			}
			//判断这个数字是否是完数
			if (a == divisorSum) {
				System.out.println(a + "    ");
			}

			//这次数字的因子之和归零,方便下一个数字的因子求和
			divisorSum = 0;
		}
	}
}
