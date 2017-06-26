package com.kmecpp.jlib.utils;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

	private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

	public static int rand(int least, int bound) {
		return RAND.nextInt(least, bound);
	}

}
