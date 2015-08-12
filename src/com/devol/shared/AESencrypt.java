package com.devol.shared;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.geronimo.mail.util.Base64Encoder;





public class AESencrypt {
	private static final String ALGORITMO="AES";
	private static final byte[] KEYVALUE=new byte[]{'N','u','1','1','q','s','0','f','t','F','i','1','0','s','0','f'};
	
	
	private static SecretKeySpec generarKey()throws Exception{
		SecretKeySpec  llave=new SecretKeySpec(KEYVALUE,ALGORITMO);
		return llave;
	}
	
	public static String encrypt(String data)throws Exception{
		SecretKeySpec key = generarKey();
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes("ISO-8859-1"));        
        StringBuffer stringBuffer = new StringBuffer();
        Base64Encoder encoder=new Base64Encoder();
        encoder.encodeWordData(encVal, stringBuffer);
        return stringBuffer.toString();
	}
	
	public static String decrypt(String encryptedData) throws Exception,IOException {
		SecretKeySpec key = generarKey();
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.DECRYPT_MODE, key); 
        Base64Encoder decoder=new Base64Encoder();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        decoder.decode(encryptedData, out);
        byte[] decValue = c.doFinal(out.toByteArray());
        String decryptedValue = new String(decValue,"ISO-8859-1");
        return decryptedValue;
    }
}