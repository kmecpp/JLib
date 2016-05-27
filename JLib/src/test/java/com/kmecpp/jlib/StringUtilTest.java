package com.kmecpp.jlib;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testVowel() {
		assertFalse(StringUtil.vowel("H3!lo"));
		assertFalse(StringUtil.vowel(""));
		assertFalse(StringUtil.vowel("hi"));

		assertTrue(StringUtil.vowel("aello"));
		assertTrue(StringUtil.vowel("O!"));
		assertTrue(StringUtil.vowel("ibe"));
	}

}