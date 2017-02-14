package com.form2bgames.megarisk.api.clhandlers;

import com.form2bgames.megarisk.api.cl.CLHandler;

public class QuitHandler implements CLHandler{

	@Override
	public String getHandledString() {
		// TODO Auto-generated method stub
		return "quit";
	}

	@Override
	public void handleString(String[] toHandle) {
		System.out.printf("Exiting normally\n");
		System.exit(0);
	}

}
