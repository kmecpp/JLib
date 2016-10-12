package com.kmecpp.jlib.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

	public static <T> T newInstance(Constructor<T> constructor) {
		return newInstance(constructor, (Object[]) null);
	}

	public static <T> T newInstance(Constructor<T> constructor, Object... values) {
		try {
			return constructor.newInstance((Object[]) values);
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ReflectionException(e);
		}
	}

	public static <T> T newInstance(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch (SecurityException | InstantiationException | IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Constructor<T> getConstructor(Class<T> cls, Class<?>[] paramTypes) {
		for (Constructor<T> constructor : (Constructor<T>[]) cls.getDeclaredConstructors()) {
			constructor.setAccessible(true);
			if (constructor.getParameterTypes().equals(paramTypes)) {
				return constructor;
			}
		}
		return null;
		//		throw new ReflectionException("Constructor does not exist!");
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeStaticMethod(Class<?> cls, String methodName, Object... params) {
		try {
			return (T) getMethod(cls, methodName, params)
					.invoke(null, (Object[]) params);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ReflectionException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Object obj, String methodName, Object... params) {
		try {
			return (T) getMethod(obj.getClass(), methodName, params)
					.invoke(obj, (Object[]) params);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ReflectionException(e);
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
			throw new ReflectionException(e);
		}
	}

	public static Object getFieldValue(Object object, String fieldName) {
		return getFieldValue(object, fieldName, Object.class);
	}

	public static <T> T getFieldValue(Object object, String fieldName, Class<T> cast) {
		try {
			return cast.cast(getField(object.getClass(), fieldName).get(object));
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	public static Field getField(Class<?> cls, String fieldName) {
		try {
			Field field = cls.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

}
