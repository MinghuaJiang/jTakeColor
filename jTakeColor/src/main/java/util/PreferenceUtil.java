package util;

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

	public void load() {
		try {
			ps.load(new FileInputStream("takeColor.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			ps.store(new FileOutputStream("takeColor.properties"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return ps.getProperty(key);
	}

	public void setProperty(String key, String value) {
		ps.setProperty(key, value);
	}

}
