package gui.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.CustomButton;
import gui.theme.GuiStyle;
import gui.windows.HomeWindow;

import java.awt.*;

public final class HomeOption {

	private HomeOption() {
	}

	public static void show(JFrame parent) {

		JDialog dialog =
				new JDialog(
						parent,
						"Home",
						true
						);

		JPanel rootPanel =
				new JPanel(
						new BorderLayout(20, 20)
						);

		rootPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		rootPanel.setBorder(
				new EmptyBorder(
						20,
						20,
						20,
						20
						)
				);

		JLabel titleLabel =
				new JLabel(
						"Return to Home?",
						SwingConstants.CENTER
						);

		titleLabel.setFont(
				GuiStyle.dialogTitleFont()
				);

		titleLabel.setForeground(
				GuiStyle.textColor()
				);

		JLabel messageLabel =
				new JLabel(
						"<html><center>"
								+ "Are you sure you want to leave "
								+ "the current game and return to "
								+ "the home screen?"
								+ "</center></html>",
								SwingConstants.CENTER
						);

		messageLabel.setFont(
				GuiStyle.textFont()
				);

		messageLabel.setForeground(
				GuiStyle.textColor()
				);

		JPanel centerPanel =
				new JPanel();

		centerPanel.setOpaque(false);

		centerPanel.setLayout(
				new BoxLayout(
						centerPanel,
						BoxLayout.Y_AXIS
						)
				);

		messageLabel.setAlignmentX(
				Component.CENTER_ALIGNMENT
				);

		centerPanel.add(messageLabel);

		JPanel buttonPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.CENTER,
								40,
								10
								)
						);

		buttonPanel.setOpaque(false);

		CustomButton yesButton =
				new CustomButton("Yes");

		CustomButton noButton =
				new CustomButton("No");

		Dimension buttonSize =
				new Dimension(120, 45);

		yesButton.setPreferredSize(buttonSize);
		noButton.setPreferredSize(buttonSize);

		buttonPanel.add(noButton);
		buttonPanel.add(yesButton);

		rootPanel.add(
				titleLabel,
				BorderLayout.NORTH
				);

		rootPanel.add(
				centerPanel,
				BorderLayout.CENTER
				);

		rootPanel.add(
				buttonPanel,
				BorderLayout.SOUTH
				);

		yesButton.addActionListener(e -> {

			dialog.dispose();

			parent.dispose();

			SwingUtilities.invokeLater(
					HomeWindow::new
					);
		});

		noButton.addActionListener(
				e -> dialog.dispose()
				);

		dialog.setContentPane(rootPanel);

		dialog.setSize(600, 280);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(parent);

		dialog.setVisible(true);
	}
}