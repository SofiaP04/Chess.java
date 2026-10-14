package gui.theme;

import java.awt.Color;
import java.awt.Font;

public final class GuiStyle {

	private GuiStyle() {
	}

	// ---------- FONTS ----------

	public static Font titleFont(float size) {
		return new Font(
				"Serif",
				Font.BOLD | Font.ITALIC,
				(int) size
				);
	}

	public static Font menuFont() {
		return new Font(
				"Serif",
				Font.BOLD,
				15
				);
	}

	public static Font buttonFont() {
		return new Font(
				"Serif",
				Font.BOLD,
				35
				);
	}

	public static Font textFont() {
		return new Font(
				"Serif",
				Font.PLAIN,
				22
				);
	}

	public static Font dialogTitleFont() {
		return new Font(
				"Serif",
				Font.BOLD,
				32
				);
	}

	// ---------- GENERAL COLORS ----------

	public static Color backgroundColor() {
		return Color.WHITE;
	}

	public static Color textColor() {
		return new Color(40, 40, 40);
	}

	// ---------- MENU ----------

	public static Color menuColor() {
		return new Color(210, 210, 210);
	}

	public static Color menuBorderColor() {
		return new Color(150, 150, 150);
	}

	public static Color menuHoverColor() {
		return new Color(130, 130, 130);
	}

	// ---------- BUTTONS ----------

	public static Color buttonColor() {
		return new Color(174, 45, 49);
	}

	public static Color buttonHoverColor() {
		return new Color(138, 35, 38);
	}

	public static Color buttonBorderColor() {
		return new Color(120, 20, 50);
	}

	// ---------- BOARD HIGHLIGHTS ----------

	public static Color kingInCheckColor() {
		return new Color(255, 120, 120, 180);
	}

	public static Color highlightedSquareColor() {
		return new Color(255, 255, 0, 120);
	}

	public static Color selectedSquareColor() {
		return new Color(0, 0, 255, 80);
	}
	public static Color buttonTextColor() {
		return Color.WHITE;
	}

	public static Color buttonReflectionColor() {
		return new Color(255, 255, 255, 50);
	}
}
