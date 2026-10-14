package gui.dialogs;

import javax.swing.*;

import gui.components.CustomButton;
import gui.theme.GuiStyle;
import gui.windows.HomeWindow;
import gui.windows.PvPChessWindow;
import gui.windows.SinglePlayerChessWindow;

import java.awt.*;

public abstract class EndGameDialog extends JDialog {

	protected EndGameDialog(
			Window owner,
			String title
			) {

		super(
				owner,
				title,
				ModalityType.APPLICATION_MODAL
				);

		getContentPane().setBackground(
				GuiStyle.backgroundColor()
				);
	}

	protected JLabel createTitleLabel(
			String text
			) {

		JLabel label =
				new JLabel(
						text,
						SwingConstants.CENTER
						);

		label.setFont(
				GuiStyle.dialogTitleFont()
				);

		label.setForeground(
				GuiStyle.textColor()
				);

		return label;
	}

	protected JLabel createTextLabel(
			String text
			) {

		JLabel label =
				new JLabel(
						text,
						SwingConstants.CENTER
						);

		label.setFont(
				GuiStyle.textFont()
				);

		label.setForeground(
				GuiStyle.textColor()
				);

		return label;
	}

	protected void configureExitButton(
			CustomButton button,
			Window owner
			) {

		button.addActionListener(e -> {

			dispose();

			if (owner instanceof JFrame frame) {
				frame.dispose();
			}

			SwingUtilities.invokeLater(
					new Runnable() {

						@Override
						public void run() {
							new HomeWindow();
						}
					}
					);
		});
	}

	protected void configureNewGameButton(
			CustomButton button,
			Window owner
			) {

		button.addActionListener(e -> {

			dispose();

			if (owner instanceof SinglePlayerChessWindow) {

				owner.dispose();

				SwingUtilities.invokeLater(
						new Runnable() {

							@Override
							public void run() {
								new SinglePlayerChessWindow();
							}
						}
						);

				return;
			}

			if (owner instanceof PvPChessWindow) {

				owner.dispose();

				SwingUtilities.invokeLater(
						new Runnable() {

							@Override
							public void run() {
								new PvPChessWindow();
							}
						}
						);
			}
		});
	}
}