package com.form2bgames.megarisk.api.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDatabase {
	public static final String userFile="data/user/users.jdb";
	private static ArrayList<User> users=new ArrayList<User>();
	@SuppressWarnings("unchecked")
	public static void loadDB(){
		File f=new File(userFile);
		if(!f.exists()) // doesn't exist, so all the users are already in the arrayList
			return;
		
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(f));
		} catch (IOException e) {}
		
		try {
			users=(ArrayList<User>)ois.readObject();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			throw new RuntimeException("Users could not be loaded.");
		}
		try {
			ois.close();
		} catch (IOException e) {}
	}
	
	public static void writeDB(){
		File f=new File(userFile);
		if(!f.exists()){ // doesn't exist, create it
			try {
				if(!f.createNewFile())
					throw new RuntimeException("users could not be saved");
			} catch (IOException e) {
				throw new RuntimeException("users could not be saved");
			}

		}else{
			try {
				f.delete();
				if(!f.createNewFile())
					throw new RuntimeException("users could not be saved");
			} catch (IOException e) {
				throw new RuntimeException("users could not be saved");
			}
		}
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f))){
			oos.writeObject(users);
		}catch(Exception e){
			throw new RuntimeException("users could not be saved");
		}
	}
	public static boolean processLogin(String user,String pass){
		
		for(User u:users){
			LoginCredentials lc=u.getLCs();
			if(lc.getUserName().equals(user)){ // we have the correct username, cannot be more than one
				
				if(lc.isPasswordEqual(pass)){
					return true;
				}
				
				return false;
			}
		}
		
		return false;
	}
	
	public static boolean isUserNameAvailable(String unm){
		
		for(User u:users){
			if(u.getLCs().getUserName().equals(unm))
				return false;
		}
		
		return true;
	}
}