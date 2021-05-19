import java.util.Scanner;
class Test1 {
	/*
	编程题 提示用户输入年月日信息，判断这一天是这一年中的第几天并打印
	按题目的要求,可以分成三个步骤来完成
	1.提示用户输入年月日信息,获得用户输入的年月日信息
	2.根据年月日信息判断这是一年中的第几天
	3.打印出结果
	*/
	public static void main(String[] args) {
		//1.提示用户输入年月日信息,获得用户输入的年月日信息
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入你想查询的年份(四位数数字)");
		int year = scanner.nextInt();
		System.out.println("请输入你想查询的月份(不能大于12或小于0)");
		int mouth = scanner.nextInt();
		System.out.println("请输入你想查询的天数(不能大于31或小于0)");
		int day = scanner.nextInt();

		//2.根据年月日信息判断这是一年中的第几天
		/*由于一年12个月中的天数不同,所以不能直接使用循环
		所以考率用数组列出一年中每个月的天数,月份之前的天数之和再加上输入的天数就是一年中的第几天了
		*/
		//平年数组
		int[] commonYear = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		//由于闰年的二月有29天, 而平年的二月有28天,所以得创建两个数组
		//闰年数组
		int[] leapYear = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		int number = 0;
		//每次循环都需要判断是平年还是闰年,不如把判断语句放到循环语句外面
		/*for (int a = 0; a < mouth - 1; a++) {
			//年份%4,有余数的是平年,没余数的是闰年
			if (year % 4 == 0) {
				number = leapYear[a] + number;
			}else {
				number = leapYear[a] + number;
			}
		}
		*/
		//判断是平年还是闰年, 获得输入的月份之前的月份的天数之和
		if (year % 4 == 0) {
			for (int a = 0; a < mouth - 1; a++) {
				number = leapYear[a] + number;
			}
		}else {
			for (int a = 0; a < mouth - 1; a++) {
				number = commonYear[a] + number;
			}
		}
		//输入的月份之前的月份的天数之和加上输入的天数,就是输入的年月日是第几天了
		number = number + day;
		System.out.println("您输入的是一年中的第" + number + "天");
	}
}
