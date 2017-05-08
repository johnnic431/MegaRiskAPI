package com.form2bgames.megarisk.api.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.crypto.Encryption;
import com.form2bgames.megarisk.api.inputprocessor.JSONNetworkProcessor;
import com.form2bgames.megarisk.api.json.packets.DHKeyExchangeOnConnect;
import com.form2bgames.megarisk.api.json.packets.JSONPacket;
import com.form2bgames.megarisk.api.user.User;

public class NetClient implements Runnable{
	public BigInteger serverPow,netMidStep,symmetricKey,modPrime;
	private User u=null;
	private InputStream is=null;
	private OutputStream os=null;
	public byte[] rc2iv8;
	public boolean encryptionFinished=false,encryptionOnNextStep=false,clientIsVerified=false;
	private int debugid=0;
	private static int debugidcount=0;
	public NetClient(Socket accept) {
		if(accept==null)
			return;
		try {
			is=accept.getInputStream();
			os=accept.getOutputStream();
		} catch (IOException e) {}
		debugid=++debugidcount;
	}
	public void postPacket(JSONPacket packet) {
		try {
			String pkt=packet.getJSONPayload(this);
			if(encryptionFinished){
				pkt=Encryption.encrypt(Encryption.getKey(symmetricKey.toString()), rc2iv8, pkt);
			}
			if(encryptionOnNextStep)
				encryptionFinished=true;
			
			byte[] toSendBytes = pkt.getBytes();
	        int toSendLen = toSendBytes.length;
	        byte[] toSendLenBytes = new byte[4];
	        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
	        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
	        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
	        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
	        os.write(toSendLenBytes);
	        os.write(toSendBytes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public User getUser() {
		return u;
	}
	public void setUser(User u) {
		this.u = u;
	}
	@Override
	public void run() {
		if(is==null)
			return;
		postPacket(new DHKeyExchangeOnConnect());
		while(true){
			try {
				byte[] lenBytes = new byte[4];
		        is.read(lenBytes, 0, 4);
		        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
		                  ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
		        byte[] receivedBytes = new byte[len];
		        is.read(receivedBytes, 0, len);
		        String s = new String(receivedBytes, 0, len);
				if(encryptionFinished){
					s=Encryption.decrypt(Encryption.getKey(symmetricKey.toString()), rc2iv8, s);
				}
				Ason as=new Ason(s);
				
				JSONPacket js=JSONNetworkProcessor.getAppropriateHandler(as.getString("packetType"));
				
				System.out.printf("Client %d packetType %s handler %s\n",debugid,as.getString("packetType"),js.getClass().getSimpleName());
				
				if(js.requiresClientAuthed()){
					if(clientIsVerified){
						if(js!=null)
							js.processJSONPayload(this, as);
					}
				}else{
					if(js!=null)
						js.processJSONPayload(this, as);
				}
				
				System.out.printf("Client %d key %s rc2iv8 %s\n",debugid,as.getString("packetType"),Encryption.getKey(symmetricKey.toString()),new String(rc2iv8));
				
			} catch (Exception e) {
				Network.ncs.remove(this);
				return;
			}
		}
	}
}
