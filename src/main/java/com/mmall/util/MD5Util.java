package com.mmall.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "a", "b", "c", "d", "e","f" };
	
	private static String byteArraytoHexString(byte[] bytes) {
		StringBuffer resultStringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			resultStringBuffer.append(byteToHexString(bytes[i]));
		}
		return resultStringBuffer.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 返回大写MD5值
	 * @param origin
	 * @param charsetName
	 * @return
	 */
	private static String MD5Encode(String origin, String charsetName) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetName == null || "".equals(charsetName)) {
				resultString = byteArraytoHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArraytoHexString(md.digest(resultString.getBytes(charsetName)));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultString.toUpperCase();
	}
	
	public static String MD5EncodeUtf8(String origin) {
		return MD5Encode(origin, "utf-8");
	}
}
