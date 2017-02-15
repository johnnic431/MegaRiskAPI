package com.form2bgames.megarisk.api.user;

import java.security.SecureRandom;

import com.form2bgames.megarisk.api.crypto.Hashing;

public class LoginCredentials implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2538521533554418503L;
	private String userName;
	private String password;
	private String salt;

	public LoginCredentials(String userName,String password){
		this.userName=userName;
		
		SecureRandom sr=new SecureRandom();
		byte[] salt=new byte[32];
		sr.nextBytes(salt);
		this.salt=new String(salt);
		
		this.password=Hashing.sha512(password, this.salt);
		
		//TODO add salting of password, maybe bcrypt?
		
		
	}
	
	public String getUserName(){
		return userName;
	}
	
	public boolean isPasswordEqual(String pass){
		return Hashing.sha512(password,salt).equals(pass);
	}
}
