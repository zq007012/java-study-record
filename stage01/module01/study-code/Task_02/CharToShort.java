class CharToShort {
	public static void main(String[] args) {
		short s;
		char c = 'a';
		//s = c;
		/*CharToShort.java:5: 错误: 不兼容的类型: 从char转换到short可能会有损失*/
		s = (short)c;
		System.out.println("s = " + s);
	}
}
