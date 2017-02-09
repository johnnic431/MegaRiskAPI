package com.form2bgames.megarisk.api;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Encryption {
	public static String encrypt(byte[] key, byte[] iv, String unencrypted) throws Exception{
		RC2ParameterSpec ivSpec = new RC2ParameterSpec(key.length*8, iv);
		Cipher cipher = Cipher.getInstance("RC2/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "RC2"), ivSpec);
		byte[] encrypted = cipher.doFinal(unencrypted.getBytes());
		return DatatypeConverter.printBase64Binary(encrypted);
	}
	
	public static String decrypt(byte[] key, byte[] iv, String encrypted) throws Exception{
		RC2ParameterSpec ivSpec = new RC2ParameterSpec(key.length*8, iv);
		Cipher cipher = Cipher.getInstance("RC2/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "RC2"), ivSpec);
		byte[] decrypted = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));
		
		return new String(decrypted);
	}
	public static byte[] generateRC2IV(){
		SecureRandom sr=new SecureRandom();
		byte[] iv=new byte[8];
		sr.nextBytes(iv);
		return iv;
	}
	public static byte[] getnBitKey(String key,int bits){
		BigInteger b=new BigInteger(key);
		byte[] finalKey=new byte[16];
		int count=0;
		for(byte by:b.toByteArray()){
			finalKey[count++]=by;
		}
		for(int c=count;c<16;c++){
			finalKey[c]=(byte)(0x00);
		}
		return finalKey;
	}
}
