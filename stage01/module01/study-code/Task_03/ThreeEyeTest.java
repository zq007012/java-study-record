class ThreeEyeTest {
	public static void main(String[] args) {
		int a = 0;
		int b = 9;
		System.out.println(a < b ? 0 : 9);
		//0
		System.out.println(a < b ? a + 5 : b + 10);
		//5
		System.out.println("a = " + a +" ; b = " + b);
		//a = 0 ; b = 9
		System.out.println( a < b ? (a = a + 5) : (b = b + 10));
		//5
		System.out.println("a = " + a +" ; b = " + b);
		//a = 5 ; b = 9
	}
}
