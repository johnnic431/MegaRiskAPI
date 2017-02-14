package com.form2bgames.megarisk.api;

import com.form2bgames.megarisk.api.cl.CLHandler;
import com.form2bgames.megarisk.api.inputprocessor.CLInputProcessor;

public class ConsoleThread implements Runnable{

	@Override
	public void run() {
		while(true){
			String read=MRAPIMain.cons.readLine("server> ").toLowerCase();
			String[] split=read.split(" ");
			CLHandler cl=CLInputProcessor.getAppropriateHandler(split[0]);
			if(cl!=null)
				cl.handleString(split);
			else
				MRAPIMain.cons.printf("The entered command \"%s\" is invalid\n", read);
		}
	}

}
