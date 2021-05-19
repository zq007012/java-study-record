class LogicTest {
	public static void main(String[] args) {
		int a = 10;
		int b = 12;
		boolean x = a > 10 && (a = a + b) == 22;
		System.out.println("a = " + a);
		System.out.println("x = " + x);
		/*
		a = 10		//说明a = a + b根本就没被执行
		x = false
		*/

		boolean y = a == 10 || (a = a + b) == 22;
		System.out.println("a = " + a);
		System.out.println("y = " + y);
		/*
		a = 10	//说明 a = a + b就没被执行
		y = true
		*/
	}
}
