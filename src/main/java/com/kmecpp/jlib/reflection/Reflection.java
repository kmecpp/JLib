package com.kmecpp.jlib.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.kmecpp.jlib.function.Converter;
import com.kmecpp.jlib.utils.ArrayUtil;

public class Reflection {

	/**
	 * Attempts to invokes the constructor without any parameters and returns
	 * the created instance
	 * 
	 * @param constructor
	 *            the constructor to invoke
	 * @return the instance created
	 */
	public static <T> T newInstance(Constructor<T> constructor) {
		return newInstance(constructor, (Object[]) null);
	}

	public static <T> T newInstance(Constructor<T> constructor, Object... values) {
		try {
			return constructor.newInstance((Object[]) values);
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	public static <T> T newInstance(Class<T> cls, Object... params) {
		try {
			if (params.length == 0) {
				return cls.newInstance();
			} else {
				return getConstructor(cls, params).newInstance(params);
			}
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	//	@SuppressWarnings("unchecked")
	public static <T> Constructor<T> getConstructor(Class<T> cls, Object... params) {
		Class<?>[] paramTypes = ArrayUtil.getComponentType(params).equals(Class.class)
				? (Class<?>[]) params
				: ArrayUtil.convert((Object[]) params, new Converter<Object, Class<?>>() {

					@Override
					public Class<?> convert(Object obj) {
						return obj.getClass();
					}

				});

		try {
			Constructor<T> constructor = cls.getDeclaredConstructor(paramTypes);
			constructor.setAccessible(true);
			return constructor;
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeStaticMethod(Class<?> cls, String methodName, Object... params) {
		try {
			return (T) getMethod(cls, methodName, params)
					.invoke(null, (Object[]) params);
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Object obj, String methodName, Object... params) {
		try {
			return (T) getMethod(obj.getClass(), methodName, params)
					.invoke(obj, (Object[]) params);
		} catch (Exception e) {
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
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	public static Object getStaticValue(Object object, Field field) {
		return getFieldValue(null, field);
	}

	public static Object getFieldValue(Object object, String fieldName) {
		return getFieldValue(object, getField(object.getClass(), fieldName));
	}

	public static Object getFieldValue(Object object, Field field) {
		return getFieldValue(object, field, Object.class);
	}

	public static <T> T getFieldValue(Object object, Field field, Class<T> cast) {
		try {
			return cast.cast(field.get(object));
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	public static Field getField(Class<?> cls, String fieldName) {
		try {
			Field field = cls.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	public static Field[] getAllFields(Object obj) {
		Field[] fields = obj instanceof Class ? ((Class<?>) obj).getDeclaredFields() : obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
		}
		return fields;
	}

}
