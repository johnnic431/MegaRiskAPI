package com.form2bgames.megarisk.api.client;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.form2bgames.megarisk.api.crypto.Hashing;

public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6576024064908924422L;
	private String name="",authToken=null,salt=null;
	private String tokenGenDate="";
	public Client(String clName, String string) {
		name=clName;
		SecureRandom sr=new SecureRandom();
		byte[] salt=new byte[32];
		sr.nextBytes(salt);
		this.salt=new String(salt);
		setCurrentAuthToken(string);
		
		File f=new File(String.format("data/user/%s.key", clName));
		try(FileWriter fw=new FileWriter(f)){
			fw.write(string);
		}catch(Exception e){}
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
		this.authToken=Hashing.sha512(string, salt);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
	    Date dt = new Date();
	    this.tokenGenDate = sdf.format(dt);
	}
	
	public boolean isTokenEqual(String auth){
		return this.authToken==Hashing.sha512(auth, salt);
	}

	public String getTokenGenDate() {
		// TODO Auto-generated method stub
		return this.tokenGenDate;
	}
	
}
