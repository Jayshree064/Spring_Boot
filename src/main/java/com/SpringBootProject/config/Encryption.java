package com.SpringBootProject.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static String encryptPassword(String pass) throws NoSuchAlgorithmException {
		 MessageDigest obj = MessageDigest.getInstance("SHA-256");  
		 obj.update(pass.getBytes());  
		 byte[] byteArray = obj.digest();    
		 StringBuffer hexData = new StringBuffer();  
		 for (int i = 0; i < byteArray.length; i++) {  
			 hexData.append(Integer.toHexString(0xFF & byteArray[i]));  
		 }  
		 return hexData.toString();
	}
}
