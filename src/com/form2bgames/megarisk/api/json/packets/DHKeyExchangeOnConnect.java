package com.form2bgames.megarisk.api.json.packets;

import java.math.BigInteger;
import java.util.HashMap;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.DataSet;
import com.form2bgames.megarisk.api.json.JSONBuilder;
import com.form2bgames.megarisk.api.network.NetClient;

public class DHKeyExchangeOnConnect implements JSONPacket{
	
	@Override
	public String getJSONPayload(NetClient nc) {
		HashMap<String,Object> datas=new HashMap<String,Object>();
		BigInteger baseNum=new BigInteger(new Integer(new java.util.Random().nextInt(2556)+758).toString());
		datas.put("publicSeed", baseNum.toString());
		nc.modPrime=getRandomPrime();
		datas.put("publicMod", nc.modPrime.toString());
		nc.serverPow=getRandomPrime();
		nc.netMidStep=baseNum.modPow(nc.serverPow,nc.modPrime);
		System.out.println(nc.netMidStep.bitLength());
		datas.put("serverMidStep", nc.netMidStep.toString());
		JSONBuilder jsb=new JSONBuilder();
		return jsb.getJSON(getPacketTypeHandle(), new DataSet(getPacketTypeHandle(),null,datas));
	}

	@Override
	public boolean processJSONPayload(NetClient nc,Ason as) {
		return false;
	}

	@Override
	public String getPacketTypeHandle() {
		return "DH_KEY_XCHG_ONCONNECT";
	}
	
	private static BigInteger getRandomPrime(){
		try{
			BigInteger test=new BigInteger(new Long((new java.util.Random().nextLong()/2L)+(Long.MAX_VALUE/2)).toString());
			while (!test.isProbablePrime(99))
				test = test.add(new BigInteger("1"));
			return test;
		}catch(Exception e){
			return getRandomPrime();
		}
	}
}
