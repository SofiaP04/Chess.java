package gui.theme;

public class ThemeManager {

	private static Theme currentTheme;

	public static void setTheme(Theme t) {
		currentTheme = t;
	}

	public static Theme get() {
		return currentTheme;
	}
}
