package gui.dialogs;

import beans.state.Color;
import gui.components.CustomButton;
import gui.theme.GuiStyle;
import gui.theme.ThemeManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CheckmateDialog extends EndGameDialog {

	public CheckmateDialog(
			Window owner,
			Color winner
			) {

		super(owner, "Checkmate");

		JPanel root =
				new JPanel(
						new BorderLayout(25, 0)
						);

		root.setBackground(
				GuiStyle.backgroundColor()
				);

		root.setBorder(
				new EmptyBorder(
						30,
						30,
						30,
						30
						)
				);

		String kingPath =
				winner == Color.WHITE
				? ThemeManager.get().get("wking")
						: ThemeManager.get().get("bking");

		ImageIcon icon =
				new ImageIcon(
						getClass().getResource(
								kingPath
								)
						);

		Image scaled =
				icon.getImage()
				.getScaledInstance(
						160,
						160,
						Image.SCALE_SMOOTH
						);

		JLabel kingLabel =
				new JLabel(
						new ImageIcon(scaled)
						);

		kingLabel.setHorizontalAlignment(
				SwingConstants.CENTER
				);

		JPanel rightPanel =
				new JPanel(
						new BorderLayout()
						);

		rightPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		JPanel centerPanel =
				new JPanel();

		centerPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		centerPanel.setLayout(
				new BoxLayout(
						centerPanel,
						BoxLayout.Y_AXIS
						)
				);

		JLabel titleLabel =
				createTitleLabel(
						"♔ CHECKMATE ♔"
						);

		titleLabel.setAlignmentX(
				Component.CENTER_ALIGNMENT
				);

		String winnerText;

		if (winner == Color.WHITE) {
			winnerText = "White";
		} else {
			winnerText = "Black";
		}

		JLabel winnerLabel =
				createTextLabel(
						winnerText + " Wins!"
						);

		winnerLabel.setAlignmentX(
				Component.CENTER_ALIGNMENT
				);

		centerPanel.add(
				Box.createVerticalGlue()
				);

		centerPanel.add(titleLabel);

		centerPanel.add(
				Box.createVerticalStrut(20)
				);

		centerPanel.add(winnerLabel);

		centerPanel.add(
				Box.createVerticalGlue()
				);

		CustomButton exitButton =
				new CustomButton("Exit");

		CustomButton newGameButton =
				new CustomButton("New Game");

		Dimension buttonSize =
				new Dimension(
						240,
						45
						);

		exitButton.setPreferredSize(
				buttonSize
				);

		exitButton.setMinimumSize(
				buttonSize
				);

		newGameButton.setPreferredSize(
				buttonSize
				);

		newGameButton.setMinimumSize(
				buttonSize
				);

		configureExitButton(
				exitButton,
				owner
				);

		configureNewGameButton(
				newGameButton,
				owner
				);

		JPanel buttonsPanel =
				new JPanel(
						new GridLayout(
								1,
								2,
								20,
								0
								)
						);

		buttonsPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		buttonsPanel.setBorder(
				new EmptyBorder(
						25,
						0,
						0,
						0
						)
				);

		buttonsPanel.add(exitButton);
		buttonsPanel.add(newGameButton);

		rightPanel.add(
				centerPanel,
				BorderLayout.CENTER
				);

		rightPanel.add(
				buttonsPanel,
				BorderLayout.SOUTH
				);

		root.add(
				kingLabel,
				BorderLayout.WEST
				);

		root.add(
				rightPanel,
				BorderLayout.CENTER
				);

		setContentPane(root);

		setSize(800, 300);
		setResizable(false);
		setLocationRelativeTo(owner);
	}
}