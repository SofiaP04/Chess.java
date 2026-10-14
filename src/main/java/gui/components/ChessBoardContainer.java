package gui.components;

import javax.swing.*;

import gui.theme.ThemeManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChessBoardContainer extends JPanel {

	private final BoardPanel boardPanel;
	private Image woodTexture;

	public ChessBoardContainer(BoardPanel boardPanel) {

		this.boardPanel = boardPanel;

		setLayout(null);
		add(boardPanel);

		setOpaque(false);

		String borderPath =
				ThemeManager.get()
				.get("borderTexture");

		woodTexture =
		        new ImageIcon(borderPath)
		        .getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 =
				(Graphics2D) g.create();

		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);

		int size =
				(int) (
						Math.min(
								getWidth(),
								getHeight()
								) * 0.90
						);

		int square = size / 10;
		int border = square;

		int boardSize = square * 8;
		int totalSize = boardSize + border * 2;

		int x =
				(getWidth() - totalSize) / 2;

		int y =
				(getHeight() - totalSize) / 2;

		int shadowOffset =
				border / 3;

		g2.setColor(
				new Color(
						0,
						0,
						0,
						80
						)
				);

		g2.fillRoundRect(
				x + shadowOffset,
				y + shadowOffset,
				totalSize,
				totalSize,
				30,
				30
				);

		TexturePaint tp =
				new TexturePaint(
						toBufferedImage(woodTexture),
						new Rectangle(
								0,
								0,
								200,
								200
								)
						);

		g2.setPaint(tp);

		g2.fillRoundRect(
				x,
				y,
				totalSize,
				totalSize,
				25,
				25
				);

		int goldThickness =
				Math.max(
						3,
						border / 12
						);

		int innerX =
				x + border
				- goldThickness / 2;

		int innerY =
				y + border
				- goldThickness / 2;

		int innerSize =
				boardSize + goldThickness;

		GradientPaint goldEdge =
				new GradientPaint(
						innerX,
						innerY,
						new Color(
								255,
								220,
								120
								),
						innerX + innerSize,
						innerY + innerSize,
						new Color(
								160,
								100,
								20
								)
						);

		g2.setPaint(goldEdge);

		g2.setStroke(
				new BasicStroke(
						goldThickness
						)
				);

		g2.drawRoundRect(
				innerX,
				innerY,
				innerSize,
				innerSize,
				20,
				20
				);

		// -----------------------------
		// COORDINATE
		// -----------------------------

		String files = "ABCDEFGH";

		for (int i = 0; i < 8; i++) {

			int centerX =
					x + border
					+ i * square
					+ square / 2;

			String file =
					String.valueOf(
							files.charAt(i)
							);

			drawCoordinate(
					g2,
					file,
					centerX,
					y + border / 2
					);

			drawCoordinate(
					g2,
					file,
					centerX,
					y + totalSize - border / 2
					);
		}

		for (int i = 0; i < 8; i++) {

			String rank =
					String.valueOf(8 - i);

			int centerY =
					y + border
					+ i * square
					+ square / 2;

			drawCoordinate(
					g2,
					rank,
					x + border / 2,
					centerY
					);

			drawCoordinate(
					g2,
					rank,
					x + totalSize - border / 2,
					centerY
					);
		}

		g2.dispose();
	}

	private void drawCoordinate(
			Graphics2D g2,
			String text,
			int centerX,
			int centerY
			) {

		g2.setFont(
				new Font(
						"Arial",
						Font.BOLD,
						28
						)
				);

		FontMetrics fm =
				g2.getFontMetrics();

		int textX =
				centerX
				- fm.stringWidth(text) / 2;

		int textY =
				centerY
				+ fm.getAscent() / 3;

		// contorno bianco

		g2.setColor(Color.WHITE);

		g2.drawString(text, textX - 1, textY);
		g2.drawString(text, textX + 1, textY);
		g2.drawString(text, textX, textY - 1);
		g2.drawString(text, textX, textY + 1);

		g2.drawString(text, textX - 1, textY - 1);
		g2.drawString(text, textX + 1, textY - 1);
		g2.drawString(text, textX - 1, textY + 1);
		g2.drawString(text, textX + 1, textY + 1);

		// testo nero

		g2.setColor(Color.BLACK);

		g2.drawString(
				text,
				textX,
				textY
				);
	}

	@Override
	public void doLayout() {

		super.doLayout();

		int size =
				(int) (
						Math.min(
								getWidth(),
								getHeight()
								) * 0.90
						);

		int square = size / 10;
		int border = square;

		int boardSize = square * 8;

		int totalSize =
				boardSize + border * 2;

		int x =
				(getWidth() - totalSize) / 2
				+ border;

		int y =
				(getHeight() - totalSize) / 2
				+ border;

		boardPanel.setBounds(
				x,
				y,
				boardSize,
				boardSize
				);

		boardPanel.resizeSquares(
				boardSize / 8
				);
	}

	private static BufferedImage toBufferedImage(
			Image img
			) {

		BufferedImage b =
				new BufferedImage(
						img.getWidth(null),
						img.getHeight(null),
						BufferedImage.TYPE_INT_RGB
						);

		Graphics2D g2 =
				b.createGraphics();

		g2.drawImage(
				img,
				0,
				0,
				null
				);

		g2.dispose();

		return b;
	}
}