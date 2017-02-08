package com.form2bgames.megarisk.api.json.packets;

import java.math.BigInteger;
import java.util.HashMap;

import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;

public class DHKeyExchangeOnConnect implements JSONPacket{
	
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		BigInteger baseNum=getRandomPrime();
		datas.put("publicSeed", baseNum.toString());
		nc.serverPow=getRandomPrime();
		nc.netMidStep=baseNum.pow(nc.serverPow.intValue());
		System.out.println(nc.netMidStep.bitLength());
		datas.put("serverMidStep", nc.netMidStep);
		JSONBuilder jsb=new JSONBuilder();
		return jsb.getJSON(getPacketTypeHandle(), new DataSet(getPacketTypeHandle(),null,datas));
	}

	@Override
	public boolean processJSONPayload(NetClient nc,String payload) {
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		return "DH_KEY_XCHG_ONCONNECT";
	}
	
	private static BigInteger getRandomPrime(){
		
		BigInteger test=new BigInteger(new Integer(new java.util.Random().nextInt(91)+37).toString());
		while (!test.isProbablePrime(99))
			test = test.add(new BigInteger("1"));
		return test;		
	}
}
