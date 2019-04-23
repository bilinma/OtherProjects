package com.bilin.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {
	private static final String ALGORITHM = "DES/ECB/PKCS5Padding";

	public static void main(String[] args) throws Exception {
		String md5Password = String.valueOf(System.currentTimeMillis()+10000);
		String key = "ucp123456";
		System.out.println(md5Password);
		String str = encrypt(md5Password, key);
		System.out.println("str: " + str);

		int length = 0;
		int bl = md5Password.getBytes().length;

		length = (int) (Math.ceil((bl + 1) / 8.0D) * 8.0D * 8.0D / 4.0D);
		System.out.println(length + " " + str.length());
		str = decrypt(str, key);
		System.out.println("str: " + str + "");
	}

	public static final String decrypt(String data, String key) throws Exception {
		byte[] bytes = hex2byte(data.getBytes());
		return new String(decrypt(bytes, key.getBytes()));
	}

	public static final String encrypt(String data, String key) {
		try {
			return byte2hex(encrypt(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		cipher.init(1, securekey, sr);

		return cipher.doFinal(data);
	}

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		cipher.init(2, securekey, sr);

		return cipher.doFinal(data);
	}

	public static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[(n / 2)] = ((byte) Integer.parseInt(item, 16));
		}
		return b2;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
}
