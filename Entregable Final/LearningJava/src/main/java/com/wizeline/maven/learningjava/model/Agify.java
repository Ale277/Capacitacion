package com.wizeline.maven.learningjava.model;

import java.io.Serializable;

public class Agify implements Serializable {
   
	private int age;
	
	private String count;
	 
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
    
    // standard getters and setters
}