package com.kmecpp.jlib;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * A utility class for manipulating text
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * Gets whether or not the string starts with the given prefix, ignoring
	 * case.
	 * 
	 * @param str
	 *            the string to test
	 * @param prefix
	 *            the prefix to test for
	 * @return true if the string starts with the prefix and false if it does
	 *         not
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return str.toLowerCase().startsWith(prefix.toLowerCase());
	}

	/**
	 * Gets whether or not the string contains the specified sequence
	 * 
	 * @param str
	 *            the string to test
	 * @param sequence
	 *            the sequence to search for
	 * @return true if the string contains the sequence, false otherwise
	 */
	public static boolean containsIgnoreCase(String str, String sequence) {
		return str.toLowerCase().contains(sequence.toLowerCase());
	}

	/**
	 * Splits the string into an array of its lines
	 * 
	 * @param str
	 *            the string to split
	 * @return an array of the string's lines
	 */
	public static String[] getLines(String str) {
		return str.split(System.lineSeparator());
	}

	/**
	 * Capitalizes the given String, converting the first character to uppercase
	 * and the rest to lowercase
	 * 
	 * @param string
	 *            the String to capitalize
	 * @return the capitalized version of the String
	 */
	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1).toLowerCase();
	}

	/**
	 * Tests whether or not the given string starts with a vowel, and returns
	 * true if it does and false if it does not
	 * 
	 * @param str
	 *            the String to test
	 * @return true if the String begins with a vowel and false if it does not
	 */
	public static boolean vowel(String str) {
		return str.length() > 0
				? "aeiou".indexOf(Character.toLowerCase(str.charAt(0))) >= 0
				: false;
	}

	/**
	 * Tests whether or not the given string is alphanumeric. A string is
	 * alphanumeric if it contains only ASCII alphabet letters or numbers
	 * 
	 * @param str
	 *            the string to test
	 * @return true if the string is alphanumeric, false if it is not
	 */
	public static boolean alphanumeric(String str) {
		return str.matches("^[a-zA-Z0-9]*$");
	}

	/**
	 * Repeat the given String the specified number of times
	 * 
	 * @param str
	 *            the String to repeat
	 * @param times
	 *            the number of times to repeat
	 * @return the repeated string
	 */
	public static String repeat(String str, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * Repeat the given character the specified number of times to create a
	 * String
	 * 
	 * @param char
	 *            the character to repeat
	 * @param times
	 *            the number of times to repeat
	 * @return the repeated string
	 */
	public static String repeat(char c, int times) {
		char[] chars = new char[times];
		Arrays.fill(chars, c);
		return new String(chars);
	}

	/**
	 * Gets a String of whitespace with the specified amount of characters
	 * 
	 * @param amount
	 *            the amount of whitespace characters
	 * @return the whitespace String
	 */
	public static String whitespace(int amount) {
		return repeat(' ', amount);
	}

	/**
	 * Converts the array to a String delimited by a space <br>
	 * <br>
	 * Effectively the same as calling:
	 * 
	 * <pre>
	 * StringUtil.join(" ", array);
	 * </pre>
	 * 
	 * @param arr
	 *            the array whose String representation to return
	 * @return the String representation of the contents of the array
	 */
	public static String join(String[] array) {
		return join(array, " ");
	}

	/**
	 * Converts the array to a String separated by the given delimiter
	 * 
	 * @param arr
	 *            the array whose String representation to return
	 * @param delimiter
	 *            the separator String
	 * @return the String representation of the contents of the array separated
	 *         by the given delimiter
	 */
	public static String join(Object[] arr, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append((i == 0 ? "" : delimiter) + arr[i].toString());
		}
		return sb.toString();
	}

	/**
	 * Gets the stacktrace for the given {@link Throwable} as a string
	 * 
	 * @param throwable
	 *            the throwable whose stacktrace to get
	 * @return a string representation of the throwable
	 */
	public static String getStacktrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	/**
	 * Creates a string from the given {@link Reader} by reading the stream into
	 * a char buffer for more much greater performance than
	 * {@link StringBuilder}
	 * 
	 * @param reader
	 *            the reader to read from
	 * @return a string from the given reader
	 * @throws IOException
	 *             if an I/O occurs while reading from the reader
	 */
	public static String read(Reader reader) throws IOException {
		StringWriter sw = new StringWriter();
		char[] buffer = new char[4096];
		int pos = 0;
		while ((pos = reader.read(buffer)) != -1) {
			sw.write(buffer, 0, pos);
		}
		return sw.toString();
	}

}
