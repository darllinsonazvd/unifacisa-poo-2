package com.unifacisa.entities;

public class Client extends Person {
	private String token;
	private Integer priority;

	public Client(String name, Integer cpf, Integer age, String token, Integer priority) {
		super(name, cpf, age);
		this.token = token;
		this.priority = priority;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public int getTokenOrder() {
		int tokenOrder = Integer.parseInt(this.token.substring(0, 1));
		return tokenOrder;
	}

	@Override
	public String toString() {
		return String.format("Nome: %s\nCPF: %d\nIdade: %d anos\nSenha: %s\nPrioridade: %d", this.getName(),
				this.getCpf(), this.getAge(), this.token, this.priority);
	}
}
