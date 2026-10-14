package gui.components;

import javax.swing.*;

import gui.theme.GuiStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

	private boolean hovered = false;

	public CustomButton(String text) {
		super(text);

		setFont(GuiStyle.buttonFont());
		setForeground(GuiStyle.buttonTextColor());

		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		Dimension size = new Dimension(300, 80);

		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				hovered = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hovered = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);

		Color base = hovered
				? GuiStyle.buttonHoverColor()
						: GuiStyle.buttonColor();

		GradientPaint gradient = new GradientPaint(
				0,
				0,
				base.brighter(),
				0,
				getHeight(),
				base.darker()
				);

		g2.setPaint(gradient);

		g2.fillRoundRect(
				0,
				0,
				getWidth() - 1,
				getHeight() - 1,
				40,
				40
				);

		g2.setColor(GuiStyle.buttonReflectionColor());

		g2.fillRoundRect(
				5,
				5,
				getWidth() - 10,
				getHeight() / 3,
				30,
				30
				);

		g2.dispose();

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);

		g2.setColor(GuiStyle.buttonBorderColor());

		g2.setStroke(new BasicStroke(2f));

		g2.drawRoundRect(
				0,
				0,
				getWidth() - 1,
				getHeight() - 1,
				40,
				40
				);

		g2.dispose();
	}
}