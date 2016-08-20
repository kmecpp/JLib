package com.kmecpp.jlib.object;

import java.lang.reflect.Field;
import java.util.Arrays;

public final class Objects {

	private Objects() {
	}

	/**
	 * Creates a string representation of the given object using its field names
	 * and values.
	 * <br>
	 * <br>
	 * The string is in the following format:
	 * 
	 * <pre>
	 * [field1=value, field2=value, field3=value]
	 * </pre>
	 * 
	 * @param object
	 *            the object to convert to a string
	 * @return a string representation of the object
	 */
	public static String toClassString(Object object) {
		StringBuilder sb = new StringBuilder("[");
		for (Field field : object.getClass().getDeclaredFields()) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			try {
				String value = field.getType().isArray()
						? Arrays.deepToString((Object[]) field.get(object))
						: String.valueOf(field.get(object));
				sb.append(field.getName() + "=" + value + ", ");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new Error(e);
			}
		}
		sb.setLength(sb.length() - 2);
		return sb.append("]").toString();
	}

	/**
	 * Gets the first object from the given arguments that is not null
	 *
	 * @param objects
	 *            the objects to search through
	 * @return the first non null object
	 */
	public static <T> T firstNonNull(T first, T second) {
		return first != null ? first : second;
	}

}
