package com.kmecpp.jlib.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtil {

	/**
	 * Creates a new array with the given type and length of 0.
	 * 
	 * @param type
	 *            the type of the array to create
	 * @return an empty array of the given type
	 */
	public static <T> T[] empty(Class<T> type) {
		return newInstance(type, 0);
	}

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

	/**
	 * Gets the first non-array component type of the array. Useful for
	 * retrieving the type of a multidimensional array.
	 * 
	 * @param array
	 *            the array to get the type of
	 * @return the the type of the array
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getType(T[] array) {
		Class<?> type = array.getClass();
		while (type.isArray()) {
			type = type.getComponentType();
		}
		return (Class<T>) type;
	}

	/**
	 * Flattens the given n-dimensional array to one dimension by combining the
	 * arrays
	 * 
	 * @param array
	 *            the multidimensional array to flatten
	 * @return the flattened array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] flatten(Object[] array, Class<T> cls) {
		ArrayList<T> list = new ArrayList<>();
		for (Object obj : array) {
			if (obj.getClass().isArray()) {
				list.addAll(Arrays.asList(flatten((T[]) obj, cls)));
			} else {
				list.add((T) obj);
			}
		}
		return list.toArray(empty(cls));
	}

	/**
	 * Combines the arrays into a single one.
	 * 
	 * @param arrays
	 *            the arrays to combine
	 * @return a single array containing all the elements of the originals
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] combine(T[]... arrays) {
		if (arrays.length == 0) {
			return (T[]) new Object[0];
		}
		ArrayList<T> list = new ArrayList<>();
		for (int i = 0; i < arrays.length; i++) {
			list.addAll(Arrays.asList(arrays[i]));
		}
		Class<T> type = getType((T[]) arrays);
		return list.toArray(empty(type));
	}

}
