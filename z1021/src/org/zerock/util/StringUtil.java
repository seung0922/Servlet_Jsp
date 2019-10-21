package org.zerock.util;

public class StringUtil {

	// 상태를 유지 x
	public static int getInt(String str) {

		int result = 0;

		try {

			result = Integer.parseInt(str);

		} catch (Exception e) {

		}

		return result;
	}

	public static double getDouble(String str) {

		double result = 0;

		try {

			result = Double.parseDouble(str);

		} catch (Exception e) {

		}

		return result;
	}

}
