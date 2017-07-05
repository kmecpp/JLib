//package com.kmecpp.jlib.resource.config;
//
//import java.io.IOException;
//
//public class ConfigTest extends Configuration {
//
//	public static void main(String[] args) throws IOException {
//		new ConfigTest().start();
//		System.out.println(ConfigTest.test);
//	}
//
//	@Setting("test")
//	@SettingProperties(comment = "This is a test")
//	public static boolean test;
//
//	@Setting("server")
//	@SettingProperties(comment = "This is a comment", deletable = true)
//	public static int server;
//
//	@Override
//	public String getPath() {
//		return "config.json";
//	}
//
//	@Override
//	public ConfigFormat getFormat() {
//		return ConfigFormat.JSON;
//	}
//
//}
