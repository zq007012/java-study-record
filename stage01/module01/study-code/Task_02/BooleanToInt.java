class BooleanToByte {
	public static void main(String[] args) {
		int i;
		boolean b = false;
		i = (int)b;
		/*
		BooleanToInt.java:5: 错误: 不兼容的类型: boolean无法转换为int
		*/
		System.out.println("i = " + i);
	}
}
