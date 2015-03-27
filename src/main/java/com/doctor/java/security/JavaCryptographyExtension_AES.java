package com.doctor.java.security;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.base.Preconditions;

/**
 * 
 * @author doctor
 *
 * @time 2015年3月27日  
 */
public class JavaCryptographyExtension_AES {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String key = AESUtil.generateKeyToBase64String();
		SecretKey secretKey = AESUtil.getKeyFromBase64String(key);
		String text = "hello ai doctor who";
		String encryptToBase64String = AESUtil.EncryptToBase64String(text, secretKey);
		System.out.println(encryptToBase64String);

		String decryptFromBase64String = AESUtil.decryptFromBase64String(encryptToBase64String, secretKey);
		Preconditions.checkState(text.equals(decryptFromBase64String));

	}

	private static final class AESUtil {

		private static String keyAlgorithm = "AES";
		private static String cipherAlgorithm = "AES/ECB/PKCS5Padding";

		private static int keySize = 128;

		public static String generateKeyToBase64String() throws NoSuchAlgorithmException {
			byte[] encoded = generateKey();
			return Base64.getEncoder().encodeToString(encoded);
		}

		public static byte[] generateKey() throws NoSuchAlgorithmException {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
			keyGenerator.init(keySize);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		}

		public static SecretKey getKeyFromBase64String(String base64StringKey) {
			byte[] key = Base64.getDecoder().decode(base64StringKey);
			SecretKey secretKey = new SecretKeySpec(key, keyAlgorithm);
			return secretKey;
		}

		public static String EncryptToBase64String(String plainText, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] doFinal = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.getUrlEncoder().encodeToString(doFinal);// UrlEncoder　内容，如果是cooki，这样的内容可以兼容cooki版本１
		}

		public static String decryptFromBase64String(String encryptedBase64String, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decode = Base64.getUrlDecoder().decode(encryptedBase64String);
			byte[] doFinal = cipher.doFinal(decode);
			return new String(doFinal, StandardCharsets.UTF_8);
		}
	}
}
