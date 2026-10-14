package gui.components;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

	private final Image background;

	public BackgroundPanel(String path) {

		ImageIcon icon = new ImageIcon(path);

		if (icon.getIconWidth() <= 0) {
			throw new IllegalStateException(
					"Impossibile trovare il file: " + path
					);
		}

		background = icon.getImage();

		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
}