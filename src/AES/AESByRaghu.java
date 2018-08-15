package AES;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESByRaghu {
	private static SecretKeySpec securityKey;
	private static byte[] actualKey;

	public static void setKeyData(String myKey) {
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

	}

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
	}

	public static String decryptInput(String encryptedStr, String secret) {
		try {
			setKeyData(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, securityKey);
			return new String(cipher.doFinal(encryptedStr.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] srs) {
		final String samplKey = "itisnotpassword!!";

		String actualStr = "javabyraghu@gmail.com";
		String encryptedString = encryptInput(actualStr, samplKey);
		String decryptedString = decryptInput(encryptedString, samplKey);

		System.out.println(actualStr);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
	}

}
