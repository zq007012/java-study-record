class PrimeNumber {
	public static void main(String[] args) {
		/*
		使用双重for循环打印2 ~ 100之间的所有素数
		*/
		outer: for (int i = 2; i <= 100; i++) {
			//System.out.println(i);
			for (int j = 2; j <= i - 1; j++) {
				if (i % j == 0) {
					//System.out.println(i + "不是素数");
					continue outer;
				}
			}
			System.out.println(i + "是素数");
		}
	}
}
