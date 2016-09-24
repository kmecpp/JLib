package com.kmecpp.jlib;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.Serializable;

import org.junit.Test;

import com.kmecpp.jlib.utils.StringUtil;

public class StringUtilTest {

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