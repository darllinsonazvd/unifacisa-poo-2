package com.unifacisa.entities;

public class Person {
	private String name;
	private Integer cpf;
	private Integer age;

	public Person(String name, Integer cpf, Integer age) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
