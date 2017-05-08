package com.form2bgames.megarisk.api.clhandlers;

import com.form2bgames.megarisk.api.cl.CLHandler;
import com.form2bgames.megarisk.api.user.User;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class UserCLHandler implements CLHandler {

	@Override
	public String getHandledString() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public void handleString(String[] toHandle) {
		if(toHandle.length==1){
			help();
			return;
		}
		switch(toHandle[1]){
		case "status":
			status();
			break;
		}
	}

	private void status() {
		System.out.printf("+-----User-----+----Username----+\n");
		for(User u:UserDatabase.getUsers()){
			System.out.printf("|%s|%s|\n",
					center(getTruncatedString(u.getName(),14),14),
					center(getTruncatedString(u.getLCs().getUserName(),16),16));
		}
		System.out.printf("+--------------+----------------+\n");
	}

	private void help() {
		// TODO Auto-generated method stub
		
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
    
    private String getTruncatedString(String inputString, int MAX_CHAR) {
		int maxLength = (inputString.length() < MAX_CHAR)?inputString.length():MAX_CHAR;
		return inputString.substring(0, maxLength);
	}

}
