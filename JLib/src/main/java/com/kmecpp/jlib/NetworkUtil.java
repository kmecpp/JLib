package com.kmecpp.jlib;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class NetworkUtil {

	/**
	 * Creates the {@link URL} from the given string and throws a runtime exception if a {@link MalformedURLException} occurrs
	 * 
	 * @param url
	 *            the string representation of the URL
	 * @return a URL instance of the given string
	 */
	public static URL createUrl(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the URL connection if possible
	 * 
	 * @param url
	 *            the URL for the connection
	 * @return the URL connection if it can be retrieved, null if it cannot
	 */
	public static HttpURLConnection getConnection(URL url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.addRequestProperty("Accept", "application/json");
			return connection;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method for reading the content of an HTTP URL
	 * 
	 * @param url
	 *            the URL to read from
	 * @return the data fetched from the URL
	 * @throws IOException
	 *             if a problem occurred when reading the data
	 */
	public static String get(URL url) throws IOException {
		HttpURLConnection connection = getConnection(url);
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.addRequestProperty("Content-Type", "application/json");
		connection.addRequestProperty("Accept", "application/json");

		return StringUtil.read(new InputStreamReader(connection.getInputStream()));
	}

	/**
	 * Sends an HTTP POST request to the specified URL with the given data
	 * 
	 * @param url
	 *            the URL to post the data to
	 * @param data
	 *            the data to post
	 * @return the webpage, after the data has been posted
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static String post(URL url, String data) throws IOException {
		HttpURLConnection connection = getConnection(url);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(data.getBytes().length));

		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		//Send Post
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();

		//Get Response
		return StringUtil.read(new InputStreamReader(connection.getInputStream()));
	}

}