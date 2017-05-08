package com.form2bgames.megarisk.api.game;

import java.io.Serializable;

import com.form2bgames.megarisk.api.user.User;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class PlayableEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9029577693607894908L;
	private User u;
	private String empireName=null;
	private ArmyData real,purported,surveilled;

	public PlayableEntity(User u) {
		this.u=u;
	}
	
	
	
	private boolean isUserAuthTokenCorrect(String authToken){
		return UserDatabase.confirmTokenIsUser(authToken,u);
	}

}
