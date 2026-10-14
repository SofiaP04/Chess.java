package gui.theme;

import java.util.HashMap;
import java.util.Map;

public class Theme {

	private final Map<String, String> values = new HashMap<>();

	public void set(String key, String value) {
		values.put(key, value);
	}

	public String get(String key) {
		return values.get(key);
	}

	public boolean contains(String key) {
		return values.containsKey(key);
	}
}
