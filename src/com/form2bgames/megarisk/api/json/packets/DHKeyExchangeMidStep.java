package com.form2bgames.megarisk.api.json.packets;

import java.math.BigInteger;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.network.NetClient;

public class DHKeyExchangeMidStep implements JSONPacket {
	private BigInteger clientMidStep;
	@Override
	public String getJSONPayload(NetClient nc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean processJSONPayload(NetClient nc,String payload) {
		Ason as=new Ason(payload);
		clientMidStep=new BigInteger(as.getString("data.DH_KEY_XCHG_MIDSTEP.clientMidStep"));
		nc.symmetricKey=clientMidStep.pow(nc.serverPow.intValue());
		System.out.printf("PreBits %d\n",nc.symmetricKey.bitLength());
		byte[] values=nc.symmetricKey.toByteArray(),finalArr=new byte[128];
		int cnt=0;
		for(int i=values.length-1;i>=values.length-128;i--){
			finalArr[cnt++]=values[i];
		}
		nc.symmetricKey=new BigInteger(finalArr);
		System.out.println(nc.symmetricKey.toString());
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		// TODO Auto-generated method stub
		return "DH_KEY_XCHG_MIDSTEP";
	}

}
