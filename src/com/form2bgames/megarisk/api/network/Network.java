package com.form2bgames.megarisk.api.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Network implements Runnable{
	private ServerSocket ss;
	public static ArrayList<NetClient> ncs=new ArrayList<NetClient>();
	public Network(){
		try {
			ss=new ServerSocket(9427);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				ncs.add(new NetClient(ss.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
