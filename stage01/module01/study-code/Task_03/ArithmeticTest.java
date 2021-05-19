class ArithmeticTest {
	public static void main(String[] args) {
		int a = 10;
		long b = 20L;
		//int c = a + b;//会编译错误 不兼容的类型: 从long转换到int可能会有损失
		long c = a + b;

		int d = 10;
		double e = 20.0;
		//int f = d + e;//会编译错误 不兼容的类型: 从double转换到int可能会有损失
		double f = d + e;

		float g = 5.0f;
		float h = 2.0f;
		float i = g + h;
		System.out.println("i = " + i);

		long j = 9L;
		float k = 6.0f;
		float l = j + k;
		System.out.println("l = " + l);
	}
}
