package com.form2bgames.megarisk.api.client;

import java.io.Serializable;
import java.security.SecureRandom;

import com.form2bgames.megarisk.api.Encryption;

public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6576024064908924422L;
	private String name="",authToken=null,salt=null;
	public Client(String clName, String string) {
		authToken=string;
		name=clName;
		SecureRandom sr=new SecureRandom();
		byte[] salt=new byte[32];
		sr.nextBytes(salt);
		this.salt=new String(salt);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public boolean hasAuthToken() {
		// TODO Auto-generated method stub
		return authToken!=null;
	}

	public void setCurrentAuthToken(String string) {
		this.authToken=Encryption.get_SHA_512_SecurePassword(string, salt);
		
	}
	
	public boolean isTokenEqual(String auth){
		return this.authToken==Encryption.get_SHA_512_SecurePassword(auth, salt);
	}
	
}
