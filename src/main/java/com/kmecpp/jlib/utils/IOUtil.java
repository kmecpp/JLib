package com.kmecpp.jlib.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class IOUtil {

	public static final short DEFAULT_BUFFER_SIZE = 4096;

	protected IOUtil() {
	}

	/**
	 * Reads the given URL into a byte array
	 * 
	 * @param url
	 *            the url to read
	 * @return the contents of the URL as a byte array
	 * @throws IOException
	 *             if an IO exception occurs
	 */
	public static byte[] readBytes(URL url) throws IOException {
		InputStream inputStream = url.openStream();
		ByteArrayOutputStream data = new ByteArrayOutputStream();

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int pos = 0;
		while ((pos = inputStream.read(buffer, 0, buffer.length)) != -1) {
			data.write(buffer, 0, pos);
		}

		data.flush();

		return data.toByteArray();
	}

	/**
	 * Attempts to read data into a String from the given URL and returns that
	 * String
	 * 
	 * @param url
	 *            the url to read from
	 * @return the data read from the URL
	 * @throws IOException
	 *             if an error occurs while reading from the URL
	 */
	public static String readHttpString(URL url) throws IOException {
		return readString(getHttpConnection(url).getInputStream());
	}

	/**
	 * Attempts to read data into a String from the given URL and returns that
	 * String
	 * 
	 * @param url
	 *            the url to read from
	 * @return the data read from the URL
	 * @throws IOException
	 *             if an error occurs while reading from the URL
	 */
	public static String readString(URL url) throws IOException {
		return readString(url.openStream());
	}

	/**
	 * High performance read from an {@link InputStream} into a String
	 * 
	 * @param inputStream
	 *            the input stream from which to read
	 * @return the string read from the reader
	 * @throws IOException
	 *             if an IOException occurs
	 */
	private static String readString(InputStream inputStream) throws IOException {
		InputStreamReader reader = new InputStreamReader(inputStream);
		StringWriter sw = new StringWriter();
		char[] buffer = new char[4096];
		int pos = 0;
		while ((pos = reader.read(buffer)) != -1) {
			sw.write(buffer, 0, pos);
		}
		return sw.toString();
	}

	public static HttpURLConnection getHttpConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		return connection;
	}

}
