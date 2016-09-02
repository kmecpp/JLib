package com.kmecpp.jlib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class IOUtil {

	private IOUtil() {
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
	public static String read(URL url) throws IOException {
		return StringUtil.read(new InputStreamReader(url.openStream()));
	}

}
