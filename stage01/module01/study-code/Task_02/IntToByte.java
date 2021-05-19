class IntToByte {
	public static void main(String[] args) {
		int a = 129;//0000 0000 0000 0000 0000 0000 1000 0001 
		byte b = (byte)a;
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println((int)((char)0b11111111111111111));
		System.out.println((short)(char)0b11111111111111111);
		System.out.println((int)'u');
		System.out.println(-2147483648-1);
		System.out.println(0b00000000000000000000000000000 - 0b1);
		System.out.println(0b11111111111111111111111111111110 - 0b11111111111111111111111111111111);
		/*
			a = 129
			b = -127
			65535
			-1
			117
			2147483647
			-1
			-1
		*/
		
	}
}
