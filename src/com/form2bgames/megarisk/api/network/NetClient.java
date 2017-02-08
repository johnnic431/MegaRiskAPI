package com.form2bgames.megarisk.api.network;

import java.math.BigInteger;

import com.form2bgames.megarisk.api.json.packets.JSONPacket;
import com.form2bgames.megarisk.api.user.User;

public class NetClient {
	public BigInteger serverPow,netMidStep,symmetricKey;
	private User u;
	public void postPacket(JSONPacket packet) {
		// TODO Auto-generated method stub
		
	}
	public User getUser() {
		return u;
	}
	public void setUser(User u) {
		this.u = u;
	}
}
