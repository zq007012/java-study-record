package com.lagou.zq.code.task2;

public class Student {
	private String name;
	private int studentNumber;
	Student(String name , int studentNumber) {
		setName(name);
		setStudentNumber(studentNumber);
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setStudentNumber(int studentNumber){
		this.studentNumber = studentNumber;
	}

	public String getName(){
		return name;
	}
	public int getStudentNumber(){
		return studentNumber;
	}
}
