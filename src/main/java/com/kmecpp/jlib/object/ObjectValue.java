package com.kmecpp.jlib.object;

public class ObjectValue {

	private final Object object;

	private ObjectValue(Object object) {
		this.object = object;
	}

	public static ObjectValue of(Object object) {
		return new ObjectValue(object);
	}

	public boolean isNull() {
		return object == null;
	}

	public String asString() {
		return (String) object;
	}

	public String asString(String def) {
		return isNull() ? def : asString();
	}

	public boolean asBoolean() {
		return (boolean) object;
	}

	public boolean asBoolean(boolean def) {
		return isNull() ? def : asBoolean();
	}

	public int asInt() {
		return (int) object;
	}

	public int asInt(int def) {
		return isNull() ? def : asInt();
	}

	public long asLong() {
		return (long) object;
	}

	public long asLong(long def) {
		return isNull() ? def : asLong();
	}

	public float asFloat() {
		return (float) object;
	}

	public float asFloat(float def) {
		return isNull() ? def : asFloat();
	}

	public double asDouble() {
		return (double) object;
	}

	public double asDouble(double def) {
		return isNull() ? def : asDouble();
	}

}
