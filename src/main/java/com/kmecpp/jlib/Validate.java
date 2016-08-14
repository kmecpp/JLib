package com.kmecpp.jlib;

public final class Validate {

	private Validate() {
	}

	/**
	 * Validates that an array has the specified length. If it does not, an
	 * {@link IllegalArgumentException} is thrown
	 * 
	 * @param array
	 *            the array to test
	 * @param length
	 *            the only permitted array length
	 * @throws IllegalArgumentException
	 *             if the array length is invalid
	 */
	public static void length(Object[] array, int length) {
		if (array.length != length) {
			throw new IllegalArgumentException("Invalid array length: " + array.length + ", expecting length: " + length);
		}
	}

	/**
	 * Validates that an array has a length within the specified range,
	 * inclusive.
	 * 
	 * @param arr
	 *            the array to test
	 * @param min
	 *            the minimum permitted array length
	 * @param max
	 *            the maximum permitted array length
	 * @throws IllegalArgumentException
	 *             if the array length is invalid
	 */
	public static void length(Object[] array, int min, int max) {
		if (array.length < min || array.length > max) {
			throw new IllegalArgumentException("Invalid array length: " + array.length + ", expecting length: " + min + "-" + max);
		}
	}

	/**
	 * Validates that an array has a length under the given value
	 * 
	 * @param array
	 *            the array to test
	 * @param max
	 *            the maximum permitted array length
	 * @throws IllegalArgumentException
	 *             if the array length is invalid
	 */
	public static void maxLength(Object[] array, int max) {
		if (array.length > max) {
			throw new IllegalArgumentException("Invalid array length: " + array.length + ", maximum length: " + max);
		}
	}

	/**
	 * Validates that an array has a length above the given value
	 * 
	 * @param arr
	 *            the array to test
	 * @param length
	 *            the minimum permitted array length
	 * @throws IllegalArgumentException
	 *             if the array length is invalid
	 */
	public static void minLength(Object[] array, int min) {
		if (array.length < min) {
			throw new IllegalArgumentException("Invalid array length: " + array.length + ", minimum length: " + min);
		}
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