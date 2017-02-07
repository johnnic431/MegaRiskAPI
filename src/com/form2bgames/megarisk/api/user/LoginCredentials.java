package com.form2bgames.megarisk.api.user;

public class LoginCredentials implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2538521533554418503L;
	private String userName;
	private String password;

	public LoginCredentials(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public boolean isPasswordEqual(String pass){
		return password.equals(pass);
	}
}
