import java.util.Scanner;
class IfMaxTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入整数a :");
		int a = scanner.nextInt();
		System.out.println("请输入整数b :");
		int b = scanner.nextInt();
		if (a > b) {
			System.out.println(a + "比较大");
		}else if (a < b) {
			System.out.println(b + "比较大");
		}else {
			System.out.println(a + "与" + b + "的值相同");
		}
	}
}
