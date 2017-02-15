package com.form2bgames.megarisk.api.json.packets;

import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.APIInfo;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.client.ClientDatabase;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;

public class ClientVerifyIsValid implements JSONPacket{
	private boolean clientVerified=false,apiVersionGood=false;
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		datas.put("verifySuccessful",clientVerified);
		datas.put("apiVerGood",apiVersionGood);
		datas.put("clientAccepted",nc.clientIsVerified);
		return new JSONBuilder().getJSON(getPacketTypeHandle(), new DataSet(getPacketTypeHandle(),null,datas));
	}

	@Override
	public boolean processJSONPayload(NetClient nc, Ason as) {
		String clientName=as.getString(String.format("data.%s.clientName", getPacketTypeHandle()));
		String clientToken=as.getString(String.format("data.%s.clientToken", getPacketTypeHandle()));
		int clientNetAPI=as.getInt(String.format("data.%s.clientAPI", getPacketTypeHandle()));
		clientVerified=ClientDatabase.processVerify(clientName, clientToken);
		apiVersionGood=(clientNetAPI==APIInfo.NET_API_VERSION);
		nc.clientIsVerified=clientVerified&&apiVersionGood;
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
