package com.form2bgames.megarisk.api.inputprocessor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.form2bgames.megarisk.api.cl.CLHandler;
import com.form2bgames.megarisk.api.clhandlers.NetCLHandler;
import com.form2bgames.megarisk.api.clhandlers.QuitHandler;

public class CLInputProcessor {
	private static ArrayList<Class<?>> clazzes=new ArrayList<Class<?>>();
	static{
		clazzes.add(NetCLHandler.class);
		clazzes.add(QuitHandler.class);
	}
	public static CLHandler getAppropriateHandler(String packetType){
		for(Class<?> c:clazzes){
			try {
				CLHandler jp=(CLHandler) c.getConstructors()[0].newInstance();
				if(jp.getHandledString().equals(packetType))
					return jp;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
