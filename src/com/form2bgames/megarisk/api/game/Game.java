package com.form2bgames.megarisk.api.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.form2bgames.megarisk.api.user.User;

public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6127723326422355676L;
	public State state=State.CREATED;
	public static Game cGame;
	public String name="";
	private HashMap<User,PlayableEntity> playerEntities=new HashMap<User,PlayableEntity>();
	private ArrayList<User> mods=new ArrayList<User>();
	public Game(){}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setMods(ArrayList<User> mods){
		this.mods=mods;
	}
	
	public void setUsers(ArrayList<User> users){
		for(User u:users){
			playerEntities.put(u, new PlayableEntity(u));
		}
	}
	
	public void start(){
		
	}
	
	public static enum State implements Serializable{
		CREATED,RUNNING,FINISHED
	}
	//TODO write and read gome from a file
}
