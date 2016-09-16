package com.kmecpp.jlib.resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Resources {

	public static final ResourceLocation ROOT;

	static {
		try {
			ROOT = new ResourceLocation(Thread.currentThread()
					.getContextClassLoader()
					.getResource("")
					.toURI()
					.toURL());
		} catch (MalformedURLException | URISyntaxException e) {
			System.err.println("Unable to load root resource location!");
			throw new Error(e);
		}
	}

	public static void load(String path) throws IOException {
		ROOT.load(path);
	}

}
