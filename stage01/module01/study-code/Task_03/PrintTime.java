import java.util.Scanner;
class PrintTime {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入先要转化为时间的正整数");
		int number = s.nextInt();
		int hour;
		int minute;
		int second;
		hour = number / 3600;
		minute = number % 3600 /60;
		second = number % 3600 % 60;
		System.out.println("您输入的是" + hour + "时" + minute + "分" + second + "秒");
	}
}
