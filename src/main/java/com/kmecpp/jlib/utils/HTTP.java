package com.kmecpp.jlib.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP {

	/**
	 * Gets an {@link HttpURLConnection} for the given URL with a read and
	 * connect timeout of 5 seconds. This method is equivalent to the following:
	 * 
	 * <pre>
	 * getHttpConnection(url, 5000, 5000);
	 * </pre>
	 * 
	 * @param url
	 *            the URL to connect to
	 * @return the HTTP URL connection
	 * @throws IOException
	 *             if an IOException occurs
	 */
	public static HttpURLConnection getConnection(URL url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			return connection;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void post(URL url, String data) {
		post(getConnection(url), data);

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
	public static String read(URL url) {
		return read(getConnection(url));
	}

	public static String postAndRead(URL url, String data) {
		HttpURLConnection connection = getConnection(url);
		post(connection, data);
		return read(connection);
	}

	private static String read(HttpURLConnection connection) {
		try {
			return IOUtil.readString(connection.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void post(HttpURLConnection connection, String data) {
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.getOutputStream().write(data.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
