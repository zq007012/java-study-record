class Xor {
	public static void main(String[] args) {
		int a = 10;
		int b = 5;
		a = a ^ b;
		System.out.println("a = " + a);
		//a = 15
		a = a ^ b;
		System.out.println("a = " + a);
		//a = 10 , 当一个数据与另一个数据进行两次异或运算后,结果还是这个数据
	}
}
