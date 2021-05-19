class WhileTest {
	public static void main(String[] args) {
		int i = 19;
		while (i > 10) {
			i--;
			if (i == 15) {
				continue;
			}
			System.out.println("i = " + i);
			
		}
	}
}
