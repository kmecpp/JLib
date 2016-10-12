package com.kmecpp.jlib.utils;

import static com.kmecpp.jlib.utils.StringUtil.ensureLength;
import static com.kmecpp.jlib.utils.StringUtil.expand;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.Serializable;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testEnsureLength() {
		assertEquals(ensureLength("test", 3), "tes");
		assertEquals(ensureLength("test", 10), "test      ");
		assertEquals(ensureLength("test", 10, 1), "test      ");
		assertEquals(ensureLength("test", 10, 0), "   test   ");
		assertEquals(ensureLength("test", 11, 0), "   test    ");
		assertEquals(ensureLength("test", 5, -1), " test");
		assertEquals(ensureLength("test", 2, -1), "st");
		assertEquals(ensureLength("Tester", 5, 0, true), "Teste");
		assertEquals(ensureLength("Tester", 5, 0, false), "ester");
		assertEquals(ensureLength("Tester", 7, 0, false), " Tester");
	}

	@Test
	public void testExpand() {
		assertEquals(expand("test", 0), "test");
		assertEquals(expand("test", 1), " test ");
		assertEquals(expand("test", 2), "  test  ");
		assertEquals(expand("tester", 2), "  tester  ");
		assertEquals(expand("tester", -1), "este");
		assertEquals(expand("tester", -2), "st");
		assertEquals(expand("tester", -10), "");
	}

	@Test
	public void testEquals() {
		assertTrue(StringUtil.equals("test", "t", "test", "es"));
		assertTrue(StringUtil.equalsIgnoreCase("test", "T", "TeST", "es"));
		assertFalse(StringUtil.equals("test", "Test", "Test"));
	}

	@Test
	public void testStartsWith() {
		assertTrue(StringUtil.startsWith("test", "T", "t", "es"));
		assertTrue(StringUtil.startsWithIgnoreCase("test", "TeST", "es"));
		assertFalse(StringUtil.startsWith("test", "T", "TeST", "es"));
	}

	@Test
	public void testContains() {
		assertTrue(StringUtil.contains("test", "t", "test", "es", "st"));
		assertTrue(StringUtil.containsAll("test", "t", "test", "es", "st"));
		assertTrue(StringUtil.containsIgnoreCase("test", "t", "TeSt", "ES", "ST"));
		assertTrue(StringUtil.containsIgnoreCase("test", "t", "B"));
		assertTrue(StringUtil.containsIgnoreCase("test", "t", "B", "tester"));
		assertTrue(StringUtil.containsAllIgnoreCase("test", "t", "TeSt", "ES", "ST"));
		assertFalse(StringUtil.containsAll("test", "test", "Test"));
		assertFalse(StringUtil.containsAllIgnoreCase("test", "t", "B"));
	}

	@Test
	public void testDeleteLast() {
		assertEquals(StringUtil.deleteLast("test"), "tes");
		assertEquals(StringUtil.deleteLast(""), "");
	}

	@Test
	public void testDelete() {
		assertEquals(StringUtil.delete("test", 3), "t");
		assertEquals(StringUtil.delete("test", -3), "t");
		assertEquals(StringUtil.delete("test", 10), "");
		assertEquals(StringUtil.delete("test", -10), "");
		assertEquals(StringUtil.delete("test", -1), "tes");
		assertEquals(StringUtil.delete("test", -0), "test");
	}

	@Test
	public void testVowel() {
		assertFalse(StringUtil.vowel("H3!lo"));
		assertFalse(StringUtil.vowel(""));
		assertFalse(StringUtil.vowel("hi"));

		assertTrue(StringUtil.vowel("aello"));
		assertTrue(StringUtil.vowel("O!"));
		assertTrue(StringUtil.vowel("ibe"));
	}

	@Test
	public void testSerialization() throws InvalidClassException, NotSerializableException {
		TestSerialization test = StringUtil.deserialize(StringUtil.serialize(new TestSerialization(5)), TestSerialization.class);
		test.getValue();
	}

	public static class TestSerialization implements Serializable {

		private static final long serialVersionUID = 5357178024155181855L;
		private int value;

		public TestSerialization(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

}