package com.form2bgames.megarisk.api.json.packets;

import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.client.ClientDatabase;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;

public class ClientVerifyIsValid implements JSONPacket{
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		datas.put("authSuccessful",nc.clientIsVerified);
		return new JSONBuilder().getJSON(getPacketTypeHandle(), new DataSet(getPacketTypeHandle(),null,datas));
	}

	@Override
	public boolean processJSONPayload(NetClient nc, Ason as) {
		String clientName=as.getString(String.format("data.%s.clientName", getPacketTypeHandle()));
		String clientToken=as.getString(String.format("data.%s.clientToken", getPacketTypeHandle()));
		nc.clientIsVerified=ClientDatabase.processVerify(clientName, clientToken);
		nc.postPacket(this);
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		// TODO Auto-generated method stub
		return "CLIENT_VERIFY_VALID_ACCESS_CODE";
	}

	@Override
	public boolean requiresClientAuthed(){
		return false;
	}

}
