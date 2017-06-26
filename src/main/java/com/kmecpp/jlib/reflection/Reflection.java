package com.kmecpp.jlib.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.kmecpp.jlib.function.Converter;
import com.kmecpp.jlib.utils.ArrayUtil;

public class Reflection {

	/**
	 * Tests whether or not the class is assignable from ANY of the given
	 * options. Essentially this method calls for each class parameter in the
	 * varargs.
	 * 
	 * <pre>
	 * Class[].isAssignableFrom(cls)
	 * </pre>
	 * 
	 * @param cls
	 *            the class to test
	 * @param classes
	 *            the classes to see if the given one is assignable from
	 * @return true if the class matches any of the ones given, false otherwise.
	 */
	public static boolean isAssignable(Class<?> cls, Class<?>... classes) {
		for (Class<?> c : classes) {
			if (c.isAssignableFrom(cls)) {
				return true;
			}
		}
		return false;
	}

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

	/**
	 * Gets all the constructors of the given class which match the specified
	 * number of parameters.
	 * 
	 * @param cls
	 *            the class to search to search for constructors
	 * @param params
	 *            the parameter count of the constructors
	 * @return all the constructors of the given class which have the specified
	 *         number of parameters
	 */
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<Constructor<T>> getConstructors(Class<T> cls, int params) {
		ArrayList<Constructor<T>> constructors = new ArrayList<>();
		for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
			if (constructor.getParameterTypes().length == params) {
				constructor.setAccessible(true);
				constructors.add((Constructor<T>) constructor);
			}
		}
		return constructors;
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

	/**
	 * Gets all the fields from the object with the given annotation. The object
	 * may either be a class or an instance of one. In either case the fields
	 * will retrieved from that class.
	 * 
	 * @param obj
	 *            the object or class to search
	 * @param annotation
	 *            the annotation to filter for
	 * @return all the fields with the given annotation
	 */
	public static Field[] getFieldsWith(Object obj, Class<? extends Annotation> annotation) {
		ArrayList<Field> fields = new ArrayList<>();
		for (Field field : getClass(obj).getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(annotation)) {
				fields.add(field);
			}
		}
		return fields.toArray(new Field[0]);
	}

	public static Field[] getAllFields(Object obj) {
		Field[] fields = getClass(obj).getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
		}
		return fields;
	}

	public static Class<?> getClass(Object obj) {
		return obj instanceof Class ? (Class<?>) obj : obj.getClass();
	}

}
