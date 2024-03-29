package com.form2bgames.megarisk.api.json.packets;

import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;
import com.form2bgames.megarisk.api.user.User;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class UserLoginPacket implements JSONPacket {
	private String authToken=null;
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		datas.put("authSuccess", authToken!=null);
		datas.put("authToken", authToken);
		DataSet ds=new DataSet(getPacketTypeHandle(),null,datas);
		return new JSONBuilder().getJSON(getPacketTypeHandle(), ds);
	}

	@Override
	public boolean processJSONPayload(NetClient nc,Ason as) {
		String unm=as.getString(String.format("data.%s.username",getPacketTypeHandle()));
		String hashPass=as.getString(String.format("data.%s.hashPass",getPacketTypeHandle()));
		User u=UserDatabase.processLogin(unm, hashPass);
		if(u==null){
			nc.postPacket(this);
			return false;
		}
		authToken=UserDatabase.getLoginToken(u);
		nc.setUser(u);
		nc.postPacket(this);
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		// TODO Auto-generated method stub
		return "USER_LOGIN_CREDENTIALS_SAUTH_rev1";
	}

}
