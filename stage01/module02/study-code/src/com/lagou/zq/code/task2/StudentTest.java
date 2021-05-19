package com.lagou.zq.code.task2;

import java.util.Scanner;
class StudentTest {
	public static void main(String[] args) {
		System.out.println("请输入学生的人数: ");
		Scanner sc = new Scanner(System.in,"GBK");
		int total = sc.nextInt();

		Student[] students = new Student[total];
		for (int i = 0; i < students.length; i++) {
			System.out.println("请输入学生的姓名: ");
			String name = sc.next();
			System.out.println("请输入学生的学号: ");
			int studentNumber = sc.nextInt();
			students[i] = new Student(name , studentNumber);
		}
		System.out.println("学生的姓名和学号输入完毕");
		System.out.println("您一共输入了" + students.length + "个学生的信息");
		System.out.println("这些学生的姓名和学号是: ");
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i].getName() + " : " + students[i].getStudentNumber());
		}
	}
}
