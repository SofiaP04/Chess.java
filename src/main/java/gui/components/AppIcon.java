package gui.components;

import javax.swing.ImageIcon;

public final class AppIcon {

	private static final ImageIcon ICON =
			new ImageIcon("assets/ChessIcon.png");

	private AppIcon() {
	}

	public static ImageIcon get() {
		return ICON;
	}
}