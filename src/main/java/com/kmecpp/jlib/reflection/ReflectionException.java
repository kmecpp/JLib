package com.kmecpp.jlib.reflection;

public class ReflectionException extends RuntimeException {

	private static final long serialVersionUID = -5532112711670410302L;

	public ReflectionException() {
	}

	public ReflectionException(String message) {
		super(message);
	}

	public ReflectionException(Throwable cause) {
		super(cause);
	}

	public ReflectionException(String message, Throwable cause) {
		super(message, cause);
	}

}
