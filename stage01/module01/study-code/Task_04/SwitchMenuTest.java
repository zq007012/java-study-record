import java.util.Scanner;
class SwitchMenuTest {
	public static void main(String[] args) {
		System.out.println("-------------------------------------");
		System.out.print("[1] 学员系统                     ");
		System.out.println("[2] 管理员系统");
		System.out.println("[3] 退出系统");
		System.out.println("-------------------------------------");
		Scanner s = new Scanner(System.in);
		int choose = s.nextInt();
		switch (choose) {
		case 1:
			System.out.println("进入学员系统");
			break;
		case 2:
			System.out.println("进入管理员系统");
			break;
		case 3:
			System.out.println("正在退出系统");
			break;
		default :
			System.out.println("输入错误");
		}
	}
}
