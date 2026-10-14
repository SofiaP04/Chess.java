package gui.theme;

import beans.pieces.Piece;
import beans.state.Color;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class PieceIcons {

	private static final Map<String, Image> cache =
			new HashMap<>();

	private PieceIcons() {
	}

	public static Image getIcon(Piece piece) {

		String color =
				piece.getColor() == Color.WHITE
				? "w"
				: "b";

		String type =
				piece.getClass()
				.getSimpleName()
				.toLowerCase();

		String key =
				color + type;

		String path =
				ThemeManager.get().get(key);

		if (path == null) {
			throw new IllegalStateException(
					"Theme key not found: "
							+ key
					);
		}

		if (!cache.containsKey(path)) {

			ImageIcon icon =
					new ImageIcon(path);

			if (icon.getIconWidth() <= 0) {
				throw new IllegalStateException(
						"Image not found: "
								+ path
						);
			}

			cache.put(
					path,
					icon.getImage()
					);
		}

		return cache.get(path);
	}
}