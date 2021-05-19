class Assign {
	public static void main(String[] args) {
		//byte a = 5;
		//byte b = 6;

		byte a = 99;
		byte b = 100;

		a += b;
		//a = a + b;//错误: 不兼容的类型: 从int转换到byte可能会有损失
		//这是因为a + b执行时, a和b的直接量都被强转成了int类型, a + b的值也是int类型 , int类型的直接量赋值给byte类型的a时需要强制转换类型
		System.out.println("a = " + a);
		
		
	}
}
