package com.kmecpp.jlib;

import java.io.File;
import java.util.Calendar;

public abstract class SystemUtil {

	public static final long MEGABYTE = 1048576L;
	public static final long GIGABYTE = 1073741824L;

	/**
	 * Gets the system operating system information
	 * 
	 * @return the operating system information
	 */
	public static String getOS() {
		return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ", " + System.getProperty("os.version") + ")";
	}

	/**
	 * Gets the current system time
	 * 
	 * @return the current system time
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
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
	 * Gets the maximum amount of memory that the JVM will attempt to use in megabytes
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
	 * Gets the primary filesystem root, which is the one currently being used by the program
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
	 * Gets the amount of free disk space on the primary filesystem root in gigabytes
	 * 
	 * @return the amount of free disk space
	 */
	public static long getFreeDiskSpace() {
		return getDiskRoot().getFreeSpace() / GIGABYTE;
	}

	/**
	 * Gets the amount of used disk space on the primary filesystem root in gigabytes
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