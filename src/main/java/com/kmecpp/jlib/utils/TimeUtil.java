package com.kmecpp.jlib.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

	private static TimeZone timeZone;

	public static TimeZone getTimeZone() {
		return timeZone;
	}

	public static void setTimeZone(String id) {
		timeZone = TimeZone.getTimeZone(id);
	}

	public static Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(timeZone);
		return calendar;
	}

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
