package com.kmecpp.jlib.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public static String readString(URL url) throws IOException {
		return StringUtil.read(new InputStreamReader(url.openStream()));
	}

}
