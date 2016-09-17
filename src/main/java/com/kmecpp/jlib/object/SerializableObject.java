package com.kmecpp.jlib.object;

import java.io.Serializable;

/**
 * A container class that allows non {@link Serializable} classes to be
 * serialized indirectly
 */
@SuppressWarnings("serial")
public class SerializableObject implements Serializable {

	private final Object object;

	public SerializableObject(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

}
