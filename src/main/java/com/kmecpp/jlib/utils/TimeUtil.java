package com.kmecpp.jlib.utils;

public class TimeUtil {

	public static long millis(long start) {
		return System.currentTimeMillis() - start;
	}

	public static double seconds(long start) {
		return (System.currentTimeMillis() - start) / 1000D;
	}

	public static double minutes(long start) {
		return (System.currentTimeMillis() - start) / 1000D / 60D;
	}

	public static double hours(long start) {
		return (System.currentTimeMillis() - start) / 1000D / 60D / 60D;
	}

	public static double days(long start) {
		return (System.currentTimeMillis() - start) / 1000D / 60D / 60D / 24D;
	}

}
