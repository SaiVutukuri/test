package com.test.demo.login;

public class Login {
	private String email;
	private String password;
	private String notAvalible="Invalid Data";
	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNotAvalible() {
		return notAvalible;
	}
	public void setNotAvalible(String notAvalible) {
		this.notAvalible = notAvalible;
	}
	
}
