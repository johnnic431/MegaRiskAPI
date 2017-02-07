package com.form2bgames.megarisk.api.user;

public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2166970350536668815L;
	private String name;
	private LoginCredentials lc;
	public User(String name,LoginCredentials lc){
		this.name=name;
		this.lc=lc;
	}
	public String getName(){
		return name;
	}
	public LoginCredentials getLCs(){
		return lc;
	}
}
