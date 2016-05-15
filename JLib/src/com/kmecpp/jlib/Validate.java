package com.kmecpp.jlib;

public class Validate {

	/**
	 * Validates that an array has the specified length. If it does not, an exception is thrown
	 * 
	 * @param arr
	 *            the array to test
	 * @param length
	 *            the length of the array to test against
	 * @throws IllegalArgumentException
	 *             if the array length does not match the specified one
	 */
	public static void arrayLength(Object[] arr, int length) {
		if (arr.length != length) throw new IllegalArgumentException("Invalid array length: " + arr.length + ", expecting length: " + length);
	}

	/**
	 * Tests whether or not an object is null
	 * 
	 * @param object
	 *            the object to test
	 * @return true if the object is null and false if it's not
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * Validates that an object is not null
	 * 
	 * @param object
	 *            the object to test
	 * @return the object, if it isn't null
	 * @throws NullPointerException
	 *             if the object is null
	 */
	public static <T> T notNull(T object) {
		return notNull(object, "The object cannot be null!");
	}

	/**
	 * Validates that an object is not null
	 * 
	 * @param object
	 *            the object to test
	 * @param message
	 *            the message of the NullPointerException if the object is null
	 * @return the object, if it's not null
	 * @throws NullPointerException
	 *             if the object is null
	 */
	public static <T> T notNull(T object, String message) {
		if (object == null) throw new NullPointerException(message);
		return object;
	}

}