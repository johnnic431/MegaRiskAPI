package com.form2bgames.megarisk.api.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Network implements Runnable{
	private ServerSocket ss;
	public static ArrayList<NetClient> ncs=new ArrayList<NetClient>();
	public Network(){
		try {
			ss=new ServerSocket(8080);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				Socket s=ss.accept();
				NetClient ncl=new NetClient(s);
				ncs.add(ncl);
				new Thread(ncl).start();
				System.out.printf("New client connected! %s",s.getInetAddress().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
