package com.lagou.zq.code.task3;

class People {
	private String name;
	private int age;
	private String country;
	
	People(){}

	People(String name, int age , String country){
		setName(name);
		setAge(age);
		setCountry(country);
	}

	public String getName(){
		return name;
	}

	public int getAge(){
		return age;
	}

	public String getCountry(){
		return country;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setAge(int age){
		if (age > 0 && age <= 120) {
			this.age = age;
		}else{
			System.out.println("年龄不合理,请重新设置");
		}
		this.age = age;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public void show(){
		System.out.println("我叫" + name + ", 今年" + age + "岁了, " + "来自" + country);
	}
}
