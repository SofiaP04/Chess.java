package gui.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.CustomButton;
import gui.theme.GuiStyle;

import java.awt.*;

public class StalemateDialog extends EndGameDialog {

	public StalemateDialog(
			Window owner
			) {

		super(owner, "Draw");

		JPanel panel =
				new JPanel(
						new BorderLayout(
								20,
								20
								)
						);

		panel.setBackground(
				GuiStyle.backgroundColor()
				);

		panel.setBorder(
				new EmptyBorder(
						25,
						25,
						25,
						25
						)
				);

		JLabel title =
				createTitleLabel("Draw");

		JLabel message =
				createTextLabel(
						"<html><center>Draw by stalemate.</center></html>"
						);

		JPanel buttonPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.CENTER,
								30,
								10
								)
						);

		buttonPanel.setOpaque(false);

		CustomButton exitButton =
				new CustomButton("Exit");

		CustomButton newGameButton =
				new CustomButton("New Game");

		exitButton.setPreferredSize(
				new Dimension(
						140,
						45
						)
				);

		newGameButton.setPreferredSize(
				new Dimension(
						200,
						45
						)
				);

		configureExitButton(
				exitButton,
				owner
				);

		configureNewGameButton(
				newGameButton,
				owner
				);

		buttonPanel.add(exitButton);
		buttonPanel.add(newGameButton);

		panel.add(
				title,
				BorderLayout.NORTH
				);

		panel.add(
				message,
				BorderLayout.CENTER
				);

		panel.add(
				buttonPanel,
				BorderLayout.SOUTH
				);

		setContentPane(panel);

		setDefaultCloseOperation(
				DISPOSE_ON_CLOSE
				);

		setSize(650, 300);
		setResizable(false);
		setLocationRelativeTo(owner);
	}
}