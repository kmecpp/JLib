package com.kmecpp.jlib.object;

public class ObjectValue {

	public static final ObjectValue NULL = new ObjectValue(null);

	protected final Object object;

	protected ObjectValue(Object object) {
		this.object = object;
	}

	public static ObjectValue of(Object object) {
		return new ObjectValue(object);
	}

	public boolean is(Class<?> c) {
		return c.isAssignableFrom(object.getClass());
	}

	public <T> T as(Class<T> c) {
		return c.cast(object);
	}

	public Class<?> getValueClass() {
		return object.getClass();
	}

	public boolean isNull() {
		return object == null;
	}

	public Object asObject() {
		return object;
	}

	//String
	public boolean isString() {
		return object instanceof String;
	}

	public String asString() {
		return (String) object;
	}

	@Override
	public String toString() {
		return String.valueOf(object);
	}

	public String asString(String def) {
		return isNull() ? def : asString();
	}

	//Boolean
	public boolean isBoolean() {
		return object instanceof Boolean;
	}

	public boolean asBoolean() {
		return (boolean) object;
	}

	public boolean asBoolean(boolean def) {
		return isNull() ? def : asBoolean();
	}

	//Integer
	public boolean isInt() {
		return object instanceof Integer;
	}

	public int asInt() {
		return (int) object;
	}

	public int asInt(int def) {
		return isNull() ? def : asInt();
	}

	//Long
	public boolean isLong() {
		return object instanceof Long;
	}

	public long asLong() {
		return (long) object;
	}

	public long asLong(long def) {
		return isNull() ? def : asLong();
	}

	//Float
	public boolean isFloat() {
		return object instanceof Float;
	}

	public float asFloat() {
		return (float) object;
	}

	public float asFloat(float def) {
		return isNull() ? def : asFloat();
	}

	//Double
	public boolean isDouble() {
		return object instanceof Double;
	}

	public double asDouble() {
		return (double) object;
	}

	public double asDouble(double def) {
		return isNull() ? def : asDouble();
	}

}
