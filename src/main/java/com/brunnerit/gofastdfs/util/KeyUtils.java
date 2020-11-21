package com.brunnerit.gofastdfs.util;

/**
 * 
 * @author zhenhua wang
 *
 */
public final class KeyUtils {
	private static final java.security.SecureRandom random = new java.security.SecureRandom();
	private static final char[] CHAR_ARRAY = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	public static String generateKey() {
		return generateKey(32);
	}

	public static String generateKey(int saltLength) {
		StringBuilder salt = new StringBuilder(saltLength);
		for (int i = 0; i < saltLength; i++) {
			salt.append(CHAR_ARRAY[random.nextInt(CHAR_ARRAY.length)]);
		}
		return salt.toString();
	}
}
