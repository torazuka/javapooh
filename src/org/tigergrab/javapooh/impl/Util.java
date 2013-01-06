package org.tigergrab.javapooh.impl;

public class Util {
	public static String getHexString(final byte b) {
		String result = "";
		int bt = b & 0xFF;
		if (bt < 0x10) {
			result += "0";
		}
		result += Integer.toHexString(bt);
		return result;
	}

	public static String byteToString(final byte[] bytes) {
		String result = "";
		for (byte b : bytes) {
			result += Util.getHexString(b);
		}
		return result;
	}

	public static Object to2digitsHex(int n) {
		String result = "";
		String tmp = Integer.toHexString(n);
		if (tmp.length() < 2) {
			result += "0";
		}
		result += tmp;
		return result;
	}
}
