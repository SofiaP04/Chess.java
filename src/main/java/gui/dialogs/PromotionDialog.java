package gui.dialogs;

import beans.move.Move;
import gui.components.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PromotionDialog {

	public static Move askPromotionMove(List<Move> moves) {

		JDialog dialog = new JDialog((Frame) null, "Pawn Promotion", true);

		JPanel root = new JPanel();
		root.setBackground(Color.WHITE);
		root.setBorder(new EmptyBorder(20, 20, 20, 20));
		root.setLayout(new BorderLayout(0, 20));

		JLabel titleLabel = new JLabel(
				"Choose the piece",
				SwingConstants.CENTER
				);

		titleLabel.setFont(new Font(
				"Serif",
				Font.BOLD,
				50
				));

		root.add(titleLabel, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(
				new GridLayout(1, moves.size(), 12, 0)
				);
		buttonsPanel.setBackground(Color.WHITE);

		final Move[] selectedMove = new Move[1];

		for (Move move : moves) {

			String pieceName =
					move.getPromotionResult()
					.getClass()
					.getSimpleName();

			CustomButton button =
					new CustomButton(pieceName);

			Dimension size = new Dimension(140, 60);

			button.setPreferredSize(size);
			button.setMinimumSize(size);
			button.setMaximumSize(size);

			button.addActionListener(e -> {
				selectedMove[0] = move;
				dialog.dispose();
			});

			buttonsPanel.add(button);
		}

		root.add(buttonsPanel, BorderLayout.CENTER);

		dialog.setContentPane(root);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		return selectedMove[0];
	}
}