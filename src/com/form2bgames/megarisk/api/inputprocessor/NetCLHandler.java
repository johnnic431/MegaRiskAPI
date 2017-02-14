package com.form2bgames.megarisk.api.inputprocessor;

import java.security.SecureRandom;

import com.form2bgames.megarisk.api.cl.CLHandler;
import com.form2bgames.megarisk.api.client.Client;
import com.form2bgames.megarisk.api.client.ClientDatabase;

public class NetCLHandler implements CLHandler{
	@Override
	public String getHandledString() {
		// TODO Auto-generated method stub
		return "netcl";
	}

	/*
	 * TODO
	 * 
	 * Add remove
	 * 
	 * */
	
	@Override
	public void handleString(String[] toHandle) {
		if(toHandle.length==1)
			help();
		else if(toHandle[1].equals("--help"))
			help();
		else if(toHandle[1].equals("addcl"))
			addcl(toHandle); //add client to verified clients
		else if(toHandle[1].equals("refreshtoken"))
			refreshToken(toHandle);
		else if(toHandle[1].equals("removecl"))
			removeClient(toHandle);
	}

	private void removeClient(String[] toHandle) {
		if(toHandle.length!=3){
			help();
			return;
		}
		String clName=toHandle[2];
		Client c=ClientDatabase.getClientByName(clName);
		
		if(c==null){
			System.out.printf("Client with name \"%s\" does not exist\n",clName);
			return;
		}
		
		boolean removed=ClientDatabase.removeClient(c);
		if(removed)
			System.out.printf("Client with name \"%s\" was removed\n",clName);
		else
			System.out.printf("Client with name \"%s\" was not removed\n",clName);
	}

	private void refreshToken(String[] toHandle) {
		if(toHandle.length!=3){
			help();
			return;
		}
		String clName=toHandle[2];
		Client c=ClientDatabase.getClientByName(clName);
		
		if(c==null){
			System.out.printf("Client with name \"%s\" does not exist\n",clName);
			return;
		}
		
		SecureRandom sr=new SecureRandom();
		byte[] at=new byte[32];
		sr.nextBytes(at);
		c.setCurrentAuthToken(new String(at));
		
		System.out.printf("Client name \"%s\" has refreshed authToken of %s\n",clName,new String(at));
	}

	private void addcl(String[] toHandle) {
		if(toHandle.length!=3){
			help();
			return;
		}
		String clName=toHandle[2];
		
		if(!ClientDatabase.isClientNameAvailable(clName)){
			System.out.printf("Client with name \"%s\" already exists",clName);
			return;
		}
			
		
		SecureRandom sr=new SecureRandom();
		byte[] at=new byte[32];
		sr.nextBytes(at);
		Client c=new Client(clName,new String(at));
		
		ClientDatabase.addClient(c);
		
		System.out.printf("Client %s added with token %s\n",clName,new String(at));
		
	}

	private void help() {
		System.out.printf(	"Usage: netcl\n"
				+ 			"    addcl <clientname>\n"
				+ 			"    --help display this message\n"
				+ 			"    removecl <clientname>\n"
				+ 			"    refreshtoken <clientname>\n");
	}

}
