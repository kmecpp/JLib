package com.kmecpp.jlib.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

	@SuppressWarnings("unchecked")
	public static <T> T invokeStaticMethod(Class<?> cls, String methodName, Object... params) {
		try {
			return (T) getMethod(cls, methodName, params)
					.invoke(null, (Object[]) params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Object obj, String methodName, Object... params) {
		try {
			return (T) getMethod(obj.getClass(), methodName, params)
					.invoke(obj, (Object[]) params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getMethod(Class<?> cls, String methodName, Object... params) {
		try {
			Class<?>[] paramTypes = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				paramTypes[i] = params[i].getClass();
			}
			Method method = cls.getDeclaredMethod(methodName, paramTypes);
			method.setAccessible(true);
			return method;
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getFieldValue(Object object, String fieldName) {
		return getFieldValue(object, fieldName, Object.class);
	}

	public static <T> T getFieldValue(Object object, String fieldName, Class<T> cast) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return cast.cast(field.get(object));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
