package com.lagou.zq.code.task2;

public class Person {
	private String name;
	private int age;
	Person(String name, int age){
		setName(name);
		setAge(age);
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAge(int age){
		this.age = age;
	}

	public void show(){
		System.out.println("姓名是: " + name + " , 名字是: " + age);
	}
}
