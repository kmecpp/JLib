package com.kmecpp.jlib.object;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class ObjectsTest {

	public static class Class {

		private HashMap<String, Integer> map = new HashMap<>();
		private String s = null;
		public int integer = -10;

		{
			map.put(s, 3);
			map.put("test2", 10);
		}

	}

	@Test
	public void test() {
		assertEquals(Objects.toClassString(new Class()), "[map={null=3, test2=10}, s=null, integer=-10]");
		assertEquals(Objects.firstNonNull(null, "Test"), "Test");
		assertEquals(Objects.firstNonNull(3, 5), Integer.valueOf(3));
	}

}
