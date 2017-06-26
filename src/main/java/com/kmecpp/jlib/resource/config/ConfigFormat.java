package com.kmecpp.jlib.resource.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.kmecpp.jlib.reflection.Reflection;
import com.kmecpp.jlib.utils.IOUtil;
import com.kmecpp.jlib.utils.StringUtil;

public interface ConfigFormat {

	public static final JsonConfig JSON = new JsonConfig();
	public static final PropertiesConfig PROPERTIES = new PropertiesConfig();

	void load(Configuration config) throws IOException;

	void save(Configuration config) throws IOException;

	public class PropertiesConfig implements ConfigFormat {

		@Override
		public void load(Configuration config) throws IOException {
			String[] lines = IOUtil.readLines(config.getFile());
			ArrayList<Field> fields = new ArrayList<>(Arrays.asList(Reflection.getFieldsWith(config, Setting.class)));
			for (String line : lines) {
				try {
					line = line.trim();
					if (line.isEmpty() || line.startsWith("#")) {
						continue;
					}
					int index = line.indexOf("=");
					String key = line.substring(0, index);
					String text = line.substring(index + 1);

					Iterator<Field> iterator = fields.iterator();
					while (iterator.hasNext()) {
						Field field = iterator.next();
						if (field.getName().equalsIgnoreCase(key)) {
							iterator.remove();
							field.set(config, StringUtil.parseType(text, field.getType()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					config.drop();
				}
			}
			Iterator<Field> iterator = fields.iterator();
			while (iterator.hasNext()) {//The remaining fields have not been updated
				Field field = iterator.next();
				SettingProperties properties = field.getAnnotation(SettingProperties.class);
				if (properties != null && properties.deletable()) {
					config.addDeletedField(field);
					iterator.remove();
				}
			}
			if (!fields.isEmpty()) {
				save(config);
			}
		}

		@Override
		public void save(Configuration config) throws IOException {
			try {
				IOUtil.createFile(config.getFile());
				StringBuilder sb = new StringBuilder();
				for (Field field : Reflection.getFieldsWith(config, Setting.class)) {
					if (config.isDeleted(field)) {
						continue;
					}
					SettingProperties properties = field.getAnnotation(SettingProperties.class);
					if (properties != null && !properties.comment().isEmpty()) {
						sb.append("# " + properties.comment() + "\n");
					}
					sb.append(field.getName() + "=" + field.get(config) + "\n");
				}
				IOUtil.write(config.getFile(), sb.toString());
			} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	public static class JsonConfig implements ConfigFormat {

		@Override
		public void load(Configuration config) throws IOException {
			StringBuilder sb = new StringBuilder();
			for (String line : IOUtil.readLines(config.getFile())) {
				if (line.trim().startsWith("#")) {
					continue;
				}
				sb.append(line);
			}

			//			JsonValue json = Json.parse(sb.toString());
		}

		@Override
		public void save(Configuration config) throws IOException {
			try {
				IOUtil.createFile(config.getFile());
				StringBuilder sb = new StringBuilder();
				for (Field field : Reflection.getFieldsWith(config, Setting.class)) {
					if (config.isDeleted(field)) {
						continue;
					}
					SettingProperties properties = field.getAnnotation(SettingProperties.class);
					if (properties != null && !properties.comment().isEmpty()) {
						sb.append("# " + properties.comment() + "\n");
					}
					sb.append(field.getName() + "=" + field.get(config) + "\n");
				}
				IOUtil.write(config.getFile(), sb.toString());
			} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		//		private static Object getKey(JsonValue json, String path) {
		//			return json.isObject() ? getKey(json.asObject().get(path.substring(0, path.indexOf("."))), path.substring(path.indexOf(".")))
		//					: json.get();
		//		}

	}

}
