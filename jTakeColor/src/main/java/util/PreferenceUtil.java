package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PreferenceUtil {
	public static final String AMPLIFY = "Amplify";

	private static PreferenceUtil preference = new PreferenceUtil();
	private Properties ps;

	public PreferenceUtil() {
		ps = new Properties();
	}

	public static PreferenceUtil getInstance() {
		return preference;
	}

	public void load() throws Exception {
		File file = new File("takeColor.properties");
		if (!file.exists()) {
			file.createNewFile();
		}
		try (FileInputStream inputStream = new FileInputStream(file)) {
			ps.load(inputStream);
		}
	}

	public void save() throws Exception {
		try (FileOutputStream outputStream = new FileOutputStream(
				"takeColor.properties")) {
			ps.store(outputStream, "");
		}
	}

	public String getProperty(String key) {
		return ps.getProperty(key);
	}

	public void setProperty(String key, String value) {
		ps.setProperty(key, value);
	}

}
