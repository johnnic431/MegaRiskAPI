package com.form2bgames.megarisk.api.json.packets;

import java.math.BigInteger;
import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.Encryption;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;

public class DHKeyExchangeMidStep implements JSONPacket {
	private BigInteger clientMidStep;
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		nc.rc2iv8=Encryption.generateRC2IV();
		datas.put("RC2_IV8", new String(nc.rc2iv8));
		nc.encryptionOnNextStep=true;
		JSONBuilder jsb=new JSONBuilder();
		return jsb.getJSON(getPacketTypeHandle(), new DataSet(getPacketTypeHandle(),null,datas));
	}

	@Override
	public boolean processJSONPayload(NetClient nc,Ason as) {
		clientMidStep=new BigInteger(as.getString("data.DH_KEY_XCHG_MIDSTEP.clientMidStep"));
		nc.symmetricKey=clientMidStep.modPow(nc.serverPow,nc.modPrime);
		nc.postPacket(this);
		return true;
	}

	@Override
	public String getPacketTypeHandle() {
		return "DH_KEY_XCHG_MIDSTEP";
	}

}
