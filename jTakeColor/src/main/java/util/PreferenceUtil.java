package util;

import java.io.IOException;

import org.eclipse.jface.preference.PreferenceStore;

public class PreferenceUtil {
	private static PreferenceUtil preference = new PreferenceUtil();
	private PreferenceStore ps;

	public PreferenceUtil() {
		ps = new PreferenceStore("takeColor.properties");
	}

	public static PreferenceUtil getInstance() {
		return preference;
	}

	public PreferenceStore getPreferenceStore() {
		return ps;
	}

	public String getStringPreference(String key) {
		return ps.getString(key);
	}

	public boolean getBooleanPreference(String key) {
		return ps.getBoolean(key);
	}

	public int getIntegerPreference(String key) {
		return ps.getInt(key);
	}

	public double getDoublePreference(String key) {
		return ps.getDouble(key);
	}

	public void load() {
		try {
			ps.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			ps.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
