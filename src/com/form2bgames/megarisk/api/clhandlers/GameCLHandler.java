package com.form2bgames.megarisk.api.clhandlers;

import java.util.ArrayList;

import com.form2bgames.megarisk.api.MRAPIMain;
import com.form2bgames.megarisk.api.cl.CLHandler;
import com.form2bgames.megarisk.api.game.Game;
import com.form2bgames.megarisk.api.game.Game.State;
import com.form2bgames.megarisk.api.user.User;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class GameCLHandler implements CLHandler{
	private static ArrayList<User> mods=new ArrayList<User>();
	@Override
	public String getHandledString() {
		// TODO Auto-generated method stub
		return "game";
	}

	@Override
	public void handleString(String[] toHandle) {
		switch(toHandle[1]){
		case "create":
			createGame(toHandle);
			break;
		case "addmod":
			addMod(toHandle);
			break;
		case "commitmods":
			Game.cGame.setMods(mods);
			break;
		}
	}

	private void addMod(String[] toHandle) {
		if(toHandle.length!=3)
			System.out.printf("There must be three arguments: %s adduser <userName>\n",getHandledString());
		User u=UserDatabase.getUserByUsername(toHandle[2]);
		if(u!=null&&!mods.contains(u)){
			mods.add(u);
			System.out.printf("User \"%s\" added to game \"%s\"\n",u.getName(),Game.cGame.name);
		}else if(u==null){
			System.out.printf("User \"%s\" does not exist\n",toHandle[2]);
		}else{
			System.out.printf("User \"%s\" is already in game\n",toHandle[2]);
		}
	}

	private void createGame(String[] toHandle) {
		if(toHandle.length!=3){
			createGameHelp();
		}
		if(!(Game.cGame==null)&&Game.cGame.state==State.RUNNING){
			boolean loop=true;
			while(loop){
				System.out.printf("A game is already running, would you like to delete it? (y/n)");
				String read=MRAPIMain.cons.readLine();
				if(read.equals("n"))
					return;
				else if(read.equals("y")){
					loop=false;
				}else{
					System.out.printf("Invalid input.\n");
				}
			}
		}
		
		Game.cGame=new Game();
		Game.cGame.setName(toHandle[2]);
		
	}

	private void createGameHelp() {
		// TODO Auto-generated method stub
		
	}

}
