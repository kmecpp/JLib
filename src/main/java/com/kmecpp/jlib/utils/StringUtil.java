package com.kmecpp.jlib.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
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
	 * Deletes the specified amount of characters from the
	 * {@link StringBuilder}. If the amount value is negative the characters are
	 * deleted from right to left
	 * 
	 * @param sb
	 *            the string builder from which to delete
	 * @param amount
	 *            the amount of characters
	 * @return the string builder
	 */
	public static StringBuilder delete(StringBuilder sb, int amount) {
		if (Math.abs(amount) >= sb.length()) {
			sb.setLength(0);
		} else if (amount >= 0) {
			sb.delete(0, amount);
		} else {
			sb.setLength(sb.length() - amount);
		}
		return sb;
	}

	/**
	 * Deletes the specified amount of characters from the String. If the amount
	 * value is negative the characters are deleted from right to left. If the
	 * amount is greater than or equal to the length of the string, all
	 * characters are removed and no exception is thrown
	 * 
	 * @param sb
	 *            the string from which to delete
	 * @param amount
	 *            the amount of characters
	 * @return a new string with the characters deleted
	 */
	public static String delete(String str, int amount) {
		if (Math.abs(amount) >= str.length()) {
			return "";
		}

		return amount >= 0
				? str.substring(amount)
				: str.substring(0, str.length() + amount);
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
		return delete(sb, -1);
	}

	/**
	 * Deletes the last character from the string and returns the result
	 * 
	 * @param str
	 *            the string to delete from
	 * @return the string with the last character removed
	 */
	public static String deleteLast(String str) {
		return delete(str, -1);
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
	 * Returns either "a" or "an" depending on whether or not the starting
	 * letter in the given string is a vowel. The value will be "an" if the
	 * first letter is a vowel, and "a" if it is not. This method is useful when
	 * displaying a message with a variable word after this specific article.
	 * For example,
	 * 
	 * <br>
	 * 
	 * <pre>
	 * You selected a/an {apple/bar}
	 * </pre>
	 * 
	 * @param str
	 *            the string to test
	 * @return the proper article
	 */
	public static String article(String str) {
		return vowel(str) ? "an" : "a";
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
	public static String join(Object[] array) {
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
	 * Serializes the given object to a string
	 * 
	 * @param obj
	 *            the object to serialize
	 * @return the serialized form of the object
	 * @throws NotSerializableException
	 *             if the given object cannot be serialized
	 */
	public static String serialize(Serializable obj) throws NotSerializableException {
		ByteArrayOutputStream target = new ByteArrayOutputStream();
		try (ObjectOutputStream stream = new ObjectOutputStream(target)) {
			stream.writeObject(obj);
			return new String(target.toByteArray(), StandardCharsets.ISO_8859_1);
		} catch (NotSerializableException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not serialize the object", e);
		}
	}

	/**
	 * Deserializes an object from the string assuming its class implements the
	 * serializable interface.
	 * 
	 * @param str
	 *            the string to deserialize
	 * @return the object representation of the String
	 * @throws InvalidClassException
	 *             if the given string does not represent a valid class
	 */
	public static Object deserialize(String str) throws InvalidClassException {
		return deserialize(str, Serializable.class);
	}

	/**
	 * Deserializes an object from the string, assuming its class
	 * implements the serializable interface, and then casts it to the specified
	 * class
	 * 
	 * @param str
	 *            the string to deserialize
	 * @param c
	 *            the class to cast the object to
	 * @throws RuntimeException
	 *             if an error occurs during deserialization
	 * @return the object representation of the String
	 * @throws InvalidClassException
	 *             if the given string does not represent a valid class
	 * @throws ClassCastException
	 *             if the object is is not assignable to the type
	 */
	public static <T extends Serializable> T deserialize(String str, Class<T> c) throws InvalidClassException {
		ByteArrayInputStream target = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
		try (ObjectInputStream stream = new ObjectInputStream(target)) {
			Object object = stream.readObject();
			return c.cast(object);
		} catch (InvalidClassException e) {
			throw e;
		} catch (IOException e) {
			throw new RuntimeException("Could not deserialize the string", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found!", e);
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
