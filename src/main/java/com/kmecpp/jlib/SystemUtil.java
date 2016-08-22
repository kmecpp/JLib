package com.kmecpp.jlib;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public final class SystemUtil {

	public static final long MEGABYTE = 1048576L;
	public static final long GIGABYTE = 1073741824L;

	private static final Calendar CALENDAR = Calendar.getInstance();

	private SystemUtil() {
	}

	/**
	 * Sets the time zone this class will use to return time
	 * 
	 * @param timeZone
	 *            the new timezone
	 */
	public static void setTimeZone(TimeZone timeZone) {
		CALENDAR.setTimeZone(timeZone);
	}

	/**
	 * Gets the system operating system information
	 * 
	 * @return the operating system information
	 */
	public static String getOS() {
		return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ", " + System.getProperty("os.version") + ")";
	}

	/**
	 * Gets the current system date time as a string. The format is as specified
	 * by getDate() and getTime(), combined and separated by a space
	 * 
	 * @return the current system time date
	 */
	public static String getDateTime() {
		return getDate() + " " + getTime();
	}

	/**
	 * Gets the current system date as a string with the following format:
	 * month/day/year
	 * 
	 * @return the current system time
	 */
	public static String getDate() {
		return (CALENDAR.get(Calendar.MONTH) + 1) + "/" + CALENDAR.get(Calendar.DAY_OF_MONTH) + "/" + CALENDAR.get(Calendar.YEAR);
	}

	/**
	 * Gets the current system time in the hour:minute:second format
	 * 
	 * @return the current system time
	 */
	public static String getTime() {
		return CALENDAR.get(Calendar.HOUR) + ":" + CALENDAR.get(Calendar.MINUTE) + ":" + CALENDAR.get(Calendar.SECOND);
	}

	/**
	 * Gets the number of processors available to the JVM
	 * 
	 * @return the number of available processors
	 */
	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Gets the total free memory left available to the JVM
	 * 
	 * @return the total free memory
	 */
	public static long getFreeMemory() {
		return (Runtime.getRuntime().maxMemory() - getUsedMemory()) / MEGABYTE;
	}

	/**
	 * Gets the current used memory in megabytes
	 * 
	 * @return the current used memory
	 */
	public static long getUsedMemory() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / MEGABYTE;
	}

	/**
	 * Gets the maximum amount of memory that the JVM will attempt to use in
	 * megabytes
	 * 
	 * @return the maximum JVM memory
	 */
	public static long getTotalMemory() {
		return Runtime.getRuntime().maxMemory() / MEGABYTE;
	}

	/**
	 * Gets the current Java version
	 * 
	 * @return the Java version
	 */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	/**
	 * Gets a list of the available filesystem roots
	 * 
	 * @return the filesystem roots
	 */
	public static File[] getDiskRoots() {
		return File.listRoots();
	}

	/**
	 * Gets the primary filesystem root, which is the one currently being used
	 * by the program
	 * 
	 * @return the primary filesystem root
	 */
	public static File getDiskRoot() {
		return new File(File.separator);
		//		File file = new File(".");
		//		while (file.getParent() != null) {
		//			file = file.getParentFile();
		//		}
		//		return file;
	}

	/**
	 * Gets the amount of free disk space on the primary filesystem root in
	 * gigabytes
	 * 
	 * @return the amount of free disk space
	 */
	public static long getFreeDiskSpace() {
		return getDiskRoot().getFreeSpace() / GIGABYTE;
	}

	/**
	 * Gets the amount of used disk space on the primary filesystem root in
	 * gigabytes
	 * 
	 * @return the amount of used disk space
	 */
	public static long getUsedDiskSpace() {
		return getTotalDiskSpace() - getFreeDiskSpace();
	}

	/**
	 * Gets the total disk space on the primary filesystem root in gigabytes
	 * 
	 * @return the total disk space
	 */
	public static long getTotalDiskSpace() {
		return getDiskRoot().getTotalSpace() / GIGABYTE;
	}

}