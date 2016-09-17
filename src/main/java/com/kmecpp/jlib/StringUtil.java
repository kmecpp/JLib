package com.kmecpp.jlib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * A utility class for manipulating text
 */
public class StringUtil {

	protected StringUtil() {
	}

	/**
	 * Gets the last character of the string or null if the string is empty
	 * 
	 * @param str
	 *            the string to get the last character from
	 * @return the last character in the string
	 */
	public static Character last(String str) {
		return str.length() > 0
				? str.charAt(str.length() - 1)
				: null;
	}

	/**
	 * Deletes the last character from the {@link StringBuilder} and returns the
	 * builder
	 * 
	 * @param sb
	 *            the string builder to delete from
	 * @return the string builder
	 */
	public static StringBuilder deleteLast(StringBuilder sb) {
		sb.setLength(sb.length() - 1);
		return sb;
	}

	/**
	 * Deletes the last character from the string and returns the result
	 * 
	 * @param str
	 *            the string to delete from
	 * @return the string with the last character removed
	 */
	public static String deleteLast(String str) {
		return str.substring(0, str.length() - 1);
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
	 * Serializes any given object to a string
	 * 
	 * @param obj
	 *            the object to serialize
	 * @return the serialized form of the object
	 */
	public static String serialize(Serializable obj) {
		ByteArrayOutputStream target = new ByteArrayOutputStream();
		try (ObjectOutputStream stream = new ObjectOutputStream(target)) {
			stream.writeObject(obj);
			return target.toString();
		} catch (IOException e) {
			throw new RuntimeException("Could not serialize the object", e);
		}
	}

	/**
	 * Deserializes any given object from a string, assuming its class
	 * implements the serializable interface
	 * 
	 * @param str
	 *            the string to deserialize
	 * @return the object representation of the String
	 */
	public static Object deserialize(String str) throws ClassNotFoundException {
		return deserialize(str, Object.class);
	}

	/**
	 * Deserializes any given object from a string, assuming its class
	 * implements the serializable interface, and then casts it to the specified
	 * class
	 * 
	 * @param str
	 *            the string to deserialize
	 * @param c
	 *            the class to cast the object to
	 * @return the object representation of the String
	 */
	public static <T> T deserialize(String str, Class<T> c) throws ClassNotFoundException {
		ByteArrayInputStream target = new ByteArrayInputStream(str.getBytes());
		try (ObjectInputStream stream = new ObjectInputStream(target)) {
			return c.cast(stream.readObject());
		} catch (IOException e) {
			throw new RuntimeException("Could not deserialize the string", e);
		}
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
