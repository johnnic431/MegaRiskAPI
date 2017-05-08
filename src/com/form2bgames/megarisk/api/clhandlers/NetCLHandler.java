package com.form2bgames.megarisk.api.clhandlers;

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
		else if(toHandle[1].equals("--help")||toHandle[1].equals("help"))
			help();
		else if(toHandle[1].equals("addcl"))
			addcl(toHandle); //add client to verified clients
		else if(toHandle[1].equals("refreshtoken")||toHandle[1].equals("refresh"))
			refreshToken(toHandle);
		else if(toHandle[1].equals("removecl"))
			removeClient(toHandle);
		else if(toHandle[1].equals("writedb"))
			ClientDatabase.writeDB();
		else if(toHandle[1].equals("status"))
			status();
		else
			help();
	}

	private void status() {
		System.out.printf("+----Client----+----Token Generation Date----+\n");
		for(Client c:ClientDatabase.getClients()){
			System.out.printf("|%s|%s|\n",
					center(getTruncatedString(c.getName(),14),14),
					center(getTruncatedString(c.getTokenGenDate(),29),29));
		}
		System.out.printf("+--------------+-----------------------------+\n");
	}

	private String getTruncatedString(String inputString, int MAX_CHAR) {
		int maxLength = (inputString.length() < MAX_CHAR)?inputString.length():MAX_CHAR;
		return inputString.substring(0, maxLength);
	}
	
	public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
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
				+ 			"    (refreshtoken|refresh) <clientname>\n"
				+ 			"    status\n"
				+ 			"    writedb\n");
	}

}
