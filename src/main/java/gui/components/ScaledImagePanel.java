package gui.components;

import javax.swing.*;
import java.awt.*;

public class ScaledImagePanel extends JPanel {

	private final Image image;

	public ScaledImagePanel(String imagePath) {

		ImageIcon icon = new ImageIcon(imagePath);

		if (icon.getIconWidth() <= 0) {
			throw new IllegalStateException(
					"Impossibile trovare il file: " + imagePath
					);
		}

		this.image = icon.getImage();

		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC
				);

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		int imgWidth = image.getWidth(null);
		int imgHeight = image.getHeight(null);

		double scale = Math.min(
				(double) panelWidth / imgWidth,
				(double) panelHeight / imgHeight
				) * 0.9;

		int w = (int) (imgWidth * scale);
		int h = (int) (imgHeight * scale);

		int x = panelWidth - w - 50;
		int y = (panelHeight - h) / 2;

		g2.drawImage(image, x, y, w, h, null);

		g2.dispose();
	}
}
