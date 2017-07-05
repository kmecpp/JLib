//package com.kmecpp.jlib.resource.config;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.HashSet;
//
//import com.kmecpp.jlib.utils.IOUtil;
//
//public abstract class Configuration {
//
//	private File file;
//	private HashSet<Field> deleted = new HashSet<>();
//
//	public Configuration() {
//		this.file = new File(getPath());
//	}
//
//	public abstract String getPath();
//
//	public ConfigFormat getFormat() {
//		return ConfigFormat.JSON;
//	}
//
//	public void start() throws IOException {
//		if (!file.exists()) {
//			save();
//		}
//		load();
//	}
//
//	public File getFile() {
//		return file;
//	}
//
//	public void load() throws IOException {
//		getFormat().load(this);
//	}
//
//	public void save() throws IOException {
//		getFormat().save(this);
//	}
//
//	public void addDeletedField(Field field) {
//		deleted.add(field);
//	}
//
//	public boolean isDeleted(Field field) {
//		return deleted.contains(field);
//	}
//
//	public void drop() throws IOException {
//		IOUtil.copyFile(file, new File(file.getParentFile(), file.getName() + ".broken." + System.currentTimeMillis()));
//		load();
//	}
//
//}
