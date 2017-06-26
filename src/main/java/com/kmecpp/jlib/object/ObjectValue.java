package com.kmecpp.jlib.object;

public class ObjectValue {

	public static final ObjectValue NULL = new ObjectValue(null);

	protected final Object value;

	protected ObjectValue(Object object) {
		this.value = object;
	}

	public static ObjectValue of(Object object) {
		return new ObjectValue(object);
	}

	public boolean is(Class<?> c) {
		return c.isAssignableFrom(value.getClass());
	}

	@SuppressWarnings("unchecked")
	public <T> T as(Class<T> c) {
		if (c.isPrimitive()) {
			return (T) value;
		}
		return c.cast(value);
	}

	public Class<?> getValueClass() {
		return value.getClass();
	}

	public boolean isNull() {
		return value == null;
	}

	public Object get() {
		return value;
	}

	//String
	public boolean isString() {
		return value instanceof String;
	}

	public String asString() {
		return (String) value;
	}

	public String asString(String def) {
		return isNull() ? def : asString();
	}

	//Boolean
	public boolean isBoolean() {
		return value instanceof Boolean;
	}

	public boolean asBoolean() {
		return (boolean) value;
	}

	public boolean asBoolean(boolean def) {
		return isNull() ? def : asBoolean();
	}

	//Integer
	public boolean isInt() {
		return value instanceof Integer;
	}

	public int asInt() {
		return (int) value;
	}

	public int asInt(int def) {
		return isNull() ? def : asInt();
	}

	//Long
	public boolean isLong() {
		return value instanceof Long;
	}

	public long asLong() {
		return (long) value;
	}

	public long asLong(long def) {
		return isNull() ? def : asLong();
	}

	//Float
	public boolean isFloat() {
		return value instanceof Float;
	}

	public float asFloat() {
		return (float) value;
	}

	public float asFloat(float def) {
		return isNull() ? def : asFloat();
	}

	//Double
	public boolean isDouble() {
		return value instanceof Double;
	}

	public double asDouble() {
		return (double) value;
	}

	public double asDouble(double def) {
		return isNull() ? def : asDouble();
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
