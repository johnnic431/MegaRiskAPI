package com.form2bgames.megarisk.api.json.packets;

import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;
import com.form2bgames.megarisk.api.user.User;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class UserTokenLoginPacket implements JSONPacket {
	private boolean lSuccessful=false;
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		datas.put("authSuccess", lSuccessful);
		DataSet ds=new DataSet(getPacketTypeHandle(),null,datas);
		return new JSONBuilder().getJSON(getPacketTypeHandle(), ds);
	}

	@Override
	public boolean processJSONPayload(NetClient nc, Ason as) {
		String lToken=as.getString(String.format("data.%s.userToken",getPacketTypeHandle()));
		User u=UserDatabase.processLogin(lToken);
		if(u==null){
			nc.postPacket(this);
			return false;
		}
		lSuccessful=true;
		nc.setUser(u);
		nc.postPacket(this);
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		// TODO Auto-generated method stub
		return "USER_LOGIN_TOKEN_SAUTH_rev1";
	}

}
