package com.kmecpp.jlib.utils;

import java.lang.reflect.Array;

public class ArrayUtil {

	/**
	 * Creates a new array with the given type and length.
	 * 
	 * @param type
	 *            the type of the array to create
	 * @param length
	 *            the length of the array to create.
	 * @return a new array with the given type and length
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] newInstance(Class<T> type, int length) {
		return (T[]) Array.newInstance(type, length);
	}

}
