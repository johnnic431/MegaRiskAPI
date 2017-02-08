package com.form2bgames.megarisk.api.user;

public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2166970350536668815L;
	private String name;
	private LoginCredentials lc;
	private String currentAuthToken=null;
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
	public String getCurrentAuthToken() {
		return currentAuthToken;
	}
	public void setCurrentAuthToken(String currentAuthToken) {
		this.currentAuthToken = currentAuthToken;
	}
}
