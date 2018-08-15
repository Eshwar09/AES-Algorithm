package com.app.aes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESByRaghu {
	private static SecretKeySpec securityKey;
	private static byte[] actualKey;

	private static void setKeyData(String myKey) {
		MessageDigest sha_1 = null;
		try {
			actualKey = myKey.getBytes();

			sha_1 = MessageDigest.getInstance("SHA-1");
			actualKey = sha_1.digest(actualKey);
			actualKey = Arrays.copyOf(actualKey, 16);
			securityKey = new SecretKeySpec(actualKey, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}// setKeyData()

	public static String encryptInput(String normalStr, String secretKey) {
		try {
			setKeyData(secretKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securityKey);
			return new String(cipher.doFinal(normalStr.getBytes()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// encryptInput()

	public static String decryptInput(String encryptedStr, String secret) {
		try {
			setKeyData(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, securityKey);
			return new String(cipher.doFinal(encryptedStr.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}// /decryptInput()

	public static void main(String[] args) {
		final String sampleKey="itisnotpassword!!";
		
		String actualStr="javabyraghu@gmail.com";
		String encryptedString=encryptInput(actualStr, sampleKey);
		String decryptedString=decryptInput(encryptedString, sampleKey);
		
		
		System.out.println(actualStr);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
		
	}// main()

}// class
