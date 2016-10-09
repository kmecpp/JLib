package com.kmecpp.jlib.reflection;

import java.lang.reflect.Field;

public class Reflection {

	public static Object getField(Object object, String fieldName) {
		return getField(object, fieldName, Object.class);
	}

	public static <T> T getField(Object object, String fieldName, Class<T> cast) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return cast.cast(field.get(object));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
