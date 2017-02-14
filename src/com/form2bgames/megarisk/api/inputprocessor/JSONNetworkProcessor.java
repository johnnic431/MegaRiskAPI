package com.form2bgames.megarisk.api.inputprocessor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.form2bgames.megarisk.api.json.packets.ClientVerifyIsValid;
import com.form2bgames.megarisk.api.json.packets.DHKeyExchangeMidStep;
import com.form2bgames.megarisk.api.json.packets.DHKeyExchangeOnConnect;
import com.form2bgames.megarisk.api.json.packets.JSONPacket;
import com.form2bgames.megarisk.api.json.packets.UserLoginPacket;
import com.form2bgames.megarisk.api.json.packets.UserTokenLoginPacket;

public class JSONNetworkProcessor {
	private static ArrayList<Class<?>> clazzes=new ArrayList<Class<?>>();
	static{
		clazzes.add(DHKeyExchangeMidStep.class);
		clazzes.add(DHKeyExchangeOnConnect.class);
		clazzes.add(UserLoginPacket.class);
		clazzes.add(UserTokenLoginPacket.class);
		clazzes.add(ClientVerifyIsValid.class);
	}
	public static JSONPacket getAppropriateHandler(String packetType){
		for(Class<?> c:clazzes){
			try {
				JSONPacket jp=(JSONPacket) c.getConstructors()[0].newInstance();
				if(jp.getPacketTypeHandle().equals(packetType))
					return jp;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
