package org.spray.darkeye.utils;

import java.security.SecureRandom;

public class Utils {

	private static String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static boolean isUpperString(String str) {
		char chr = 0;
		for (int i = 0; i < str.length(); i++) {
			chr = str.charAt(i);
		}
		return Character.isUpperCase(chr);
	}

}
