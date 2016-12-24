package com.kmecpp.jlib.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

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

	public static BufferedImage getImage(String path) throws IOException {
		return ImageIO.read(getFile(path));
	}

	public static File getFile(String path) {
		return new File(ROOT.resolve(path).getFile());
	}

	public static byte[] load(String path) throws IOException {
		return ROOT.load(path);
	}

}
