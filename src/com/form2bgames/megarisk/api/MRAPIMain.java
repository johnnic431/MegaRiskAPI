package com.form2bgames.megarisk.api;

import java.io.Console;

import com.form2bgames.megarisk.api.client.ClientDatabase;
import com.form2bgames.megarisk.api.network.Network;
import com.form2bgames.megarisk.api.user.UserDatabase;

public class MRAPIMain {
	public static Console cons;

	public static void main(String[] args) {
		ClientDatabase.loadDB();
		UserDatabase.loadDB();
		new Thread(new Network()).start();
		if ((cons = System.console()) == null) {
			throw new RuntimeException("A console must be present");
		}
		new ConsoleThread().run();
		System.out.printf("Version: %s\n",APIInfo.version);
	}

}
