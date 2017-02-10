package com.form2bgames.megarisk.api.json.packets;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.network.NetClient;

public class EnsureValidKey implements JSONPacket {

	@Override
	public String getJSONPayload(NetClient nc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean processJSONPayload(NetClient nc, Ason as) {
		
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		// TODO Auto-generated method stub
		return "AUTHKEY_IS_VALID";
	}

}
