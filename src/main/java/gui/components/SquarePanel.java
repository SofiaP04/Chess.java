package gui.components;

import beans.move.Move;
import beans.pieces.Piece;
import beans.state.Coordinate;
import core.facade.GameFacade;
import core.facade.MoveSelectionFacade;
import gui.dialogs.PromotionDialog;
import gui.listeners.SquareClickListener;
import gui.theme.GuiStyle;
import gui.theme.PieceIcons;
import gui.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SquarePanel extends JPanel {

	private final int row, col;
	private final GameFacade game;
	private final MoveSelectionFacade selection;
	private final BoardPanel parent;

	private int squareSize = 64;

	private boolean highlighted = false;
	private boolean selectedSquare = false;
	private boolean kingInCheck = false;

	private Image lightSquareImg;
	private Image darkSquareImg;

	public SquarePanel(
			int row,
			int col,
			GameFacade game,
			MoveSelectionFacade selection,
			BoardPanel parent
			) {
		this.row = row;
		this.col = col;
		this.game = game;
		this.selection = selection;
		this.parent = parent;

		String lightPath = ThemeManager.get().get("lightSquare");
		String darkPath = ThemeManager.get().get("darkSquare");

		lightSquareImg = new ImageIcon(lightPath).getImage();

		darkSquareImg = new ImageIcon(darkPath).getImage();
		
		setOpaque(false);

		addMouseListener(
				new SquareClickListener(
						row,
						col,
						game,
						selection,
						parent
						)
				);
	}

	public void setSquareSize(int size) {
		this.squareSize = size;
	}

	public void setHighlighted(boolean value) {
		this.highlighted = value;
	}

	public void setSelectedSquare(boolean value) {
		this.selectedSquare = value;
	}

	public void setKingInCheck(boolean value) {
		this.kingInCheck = value;
	}



	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		boolean light = (row + col) % 2 == 0;
		Image base = light ? lightSquareImg : darkSquareImg;

		g2.drawImage(
				base,
				0,
				0,
				squareSize,
				squareSize,
				null
				);

		if (kingInCheck) {
			g2.setColor(GuiStyle.kingInCheckColor());
			g2.fillRect(0, 0, squareSize, squareSize);
		}

		if (highlighted) {
			g2.setColor(GuiStyle.highlightedSquareColor());
			g2.fillRect(0, 0, squareSize, squareSize);
		}

		if (selectedSquare) {
			g2.setColor(GuiStyle.selectedSquareColor());
			g2.fillRect(0, 0, squareSize, squareSize);
		}

		if (!parent.isAnimatingFrom(row, col)) {

			Piece[][] board = game.getBoardSnapshot();
			Piece piece = board[row][col];

			if (piece != null) {
				Image img = PieceIcons.getIcon(piece);

				g2.drawImage(
						img,
						0,
						0,
						squareSize,
						squareSize,
						this
						);
			}
		}

		g2.dispose();
	}

	public void refresh() {
		repaint();
	}
}