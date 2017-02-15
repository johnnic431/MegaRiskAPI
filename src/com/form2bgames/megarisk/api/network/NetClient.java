package com.form2bgames.megarisk.api.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.crypto.Encryption;
import com.form2bgames.megarisk.api.inputprocessor.JSONNetworkProcessor;
import com.form2bgames.megarisk.api.json.packets.JSONPacket;
import com.form2bgames.megarisk.api.user.User;

public class NetClient implements Runnable{
	public BigInteger serverPow,netMidStep,symmetricKey,modPrime;
	private User u=null;
	private BufferedWriter bw=null;
	private BufferedReader br=null;
	public byte[] rc2iv8;
	public boolean encryptionFinished=false,encryptionOnNextStep=false,clientIsVerified=false;
	public NetClient(Socket accept) {
		if(accept==null)
			return;
		try {
			br=new BufferedReader(new InputStreamReader(accept.getInputStream(),"UTF-8"));
			bw=new BufferedWriter(new OutputStreamWriter(accept.getOutputStream(),"UTF-8"));
		} catch (IOException e) {}
	}
	public void postPacket(JSONPacket packet) {
		try {
			String pkt=packet.getJSONPayload(this);
			if(encryptionFinished){
				pkt=Encryption.encrypt(Encryption.getKey(symmetricKey.toString()), rc2iv8, pkt);
			}
			if(encryptionOnNextStep)
				encryptionFinished=true;
			bw.write(pkt);
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
		if(br==null)
			return;
		while(true){
			try {
				String s=br.readLine();
				if(encryptionFinished){
					s=Encryption.decrypt(Encryption.getKey(symmetricKey.toString()), rc2iv8, s);
				}
				Ason as=new Ason(s);
				JSONPacket js=JSONNetworkProcessor.getAppropriateHandler(as.getString("packetType"));
				if(js.requiresClientAuthed()){
					if(clientIsVerified){
						if(js!=null)
							js.processJSONPayload(this, as);
					}
				}else{
					if(js!=null)
						js.processJSONPayload(this, as);
				}
				
			} catch (Exception e) {
				Network.ncs.remove(this);
				return;
			}
		}
	}
}
