package com.kmecpp.jlib.resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.kmecpp.jlib.utils.IOUtil;
import com.kmecpp.jlib.utils.StringUtil;

public class ResourceLocation {

	private URL url;

	public ResourceLocation(String path) {
		this(Resources.ROOT.resolve(path));
	}

	public ResourceLocation(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public byte[] load(String path) throws IOException {
		return IOUtil.readBytes(resolve(path));
	}

	public URL resolve(String path) {
		try {
			if (!StringUtil.last(path).equals('/')) {
				path += "/";
			}
			return new URL(url, path);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	//	private static URL parseUrl(String url) {
	//		try {
	//			return new URL(url);
	//		} catch (MalformedURLException e) {
	//			throw new RuntimeException(e);
	//		}
	//	}

}
