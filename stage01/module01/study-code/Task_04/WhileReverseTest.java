class WhileReverseTest {
	public static void main(String[] args) {
		/*
		把用户输入的正整数数字反向输出
		如:输入123 , 输出321
		*/
		//首先判断这是一个几位数
		//分别求出各个位数上的数字
		Scanner  s = new Scanner(System.in);
		int number = s.nextInt();
		int count = 0;
		while(number /= 10 >= 0){
			count++;
		}
	}
}
