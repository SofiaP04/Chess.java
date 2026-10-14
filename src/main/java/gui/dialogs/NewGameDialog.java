package gui.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.CustomButton;
import gui.listeners.PvPListener;
import gui.listeners.SinglePlayerListener;
import gui.theme.GuiStyle;
import gui.windows.PvPChessWindow;
import gui.windows.SinglePlayerChessWindow;

import java.awt.*;

public class NewGameDialog extends JDialog {

	public NewGameDialog(JFrame parent) {

		super(parent, "New Game", true);

		JPanel rootPanel = new JPanel(new BorderLayout(20, 20));
		rootPanel.setBackground(GuiStyle.backgroundColor());
		rootPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel(
				"Select Game Mode",
				SwingConstants.CENTER
				);

		titleLabel.setFont(GuiStyle.dialogTitleFont());
		titleLabel.setForeground(GuiStyle.textColor());

		rootPanel.add(titleLabel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(GuiStyle.backgroundColor());
		buttonPanel.setLayout(
				new BoxLayout(
						buttonPanel,
						BoxLayout.Y_AXIS
						)
				);

		CustomButton pvpButton =
				new CustomButton("PvP");

		CustomButton singlePlayerButton =
				new CustomButton("Single Player");

		pvpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		buttonPanel.add(pvpButton);
		buttonPanel.add(Box.createVerticalStrut(20));
		buttonPanel.add(singlePlayerButton);

		rootPanel.add(buttonPanel, BorderLayout.CENTER);

		pvpButton.addActionListener(
				new PvPListener(
						this,
						parent
						)
				);
		singlePlayerButton.addActionListener(
				new SinglePlayerListener(
						this,
						parent
						)
				);
		setContentPane(rootPanel);

		setSize(500, 350);
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	public static void show(JFrame parent) {
		new NewGameDialog(parent).setVisible(true);
	}
}