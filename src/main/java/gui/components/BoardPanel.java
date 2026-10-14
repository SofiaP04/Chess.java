package gui.components;

import beans.move.Move;
import beans.pieces.King;
import beans.pieces.Piece;
import beans.state.Color;
import beans.state.Coordinate;
import core.ai.AiPlayer;
import core.facade.GameFacade;
import core.facade.MoveSelectionFacade;
import gui.dialogs.CheckmateDialog;
import gui.theme.PieceIcons;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoardPanel extends JPanel {

	private final SquarePanel[][] squares = new SquarePanel[8][8];

	private final GameFacade game;
	private final MoveSelectionFacade selection;

	private AiPlayer aiPlayer;

	// --- ANIMAZIONE ---
	private boolean animating = false;

	private Image animPiece = null;

	private int animX;
	private int animY;

	private int startX;
	private int startY;

	private int targetX;
	private int targetY;

	private long animStartTime;

	private static final int ANIM_DURATION = 600;

	private int animFromR = -1;
	private int animFromC = -1;

	private int animToR = -1;
	private int animToC = -1;

	private Move pendingMove = null;

	public BoardPanel(
			GameFacade game,
			MoveSelectionFacade selection,
			AiPlayer aiPlayer
			) {

		this.game = game;
		this.selection = selection;
		this.aiPlayer = aiPlayer;

		setLayout(null);
		setOpaque(true);

		for (int r = 7; r >= 0; r--) {
			for (int c = 0; c < 8; c++) {

				SquarePanel sq =
						new SquarePanel(
								r,
								c,
								game,
								selection,
								this
								);

				squares[r][c] = sq;
				add(sq);
			}
		}

		resizeSquares(64);
	}

	public void setAiPlayer(AiPlayer aiPlayer) {
		this.aiPlayer = aiPlayer;
	}

	public void resizeSquares(int squareSize) {

		for (int r = 7; r >= 0; r--) {
			for (int c = 0; c < 8; c++) {

				squares[r][c].setBounds(
						c * squareSize,
						(7 - r) * squareSize,
						squareSize,
						squareSize
						);

				squares[r][c].setSquareSize(squareSize);
			}
		}
	}

	@Override
	public void doLayout() {

		super.doLayout();

		int size =
				Math.min(
						getWidth(),
						getHeight()
						) / 8;

		resizeSquares(size);
	}

	public void startAnimatedMove(
			Coordinate from,
			Coordinate to,
			Move move
			) {

		Piece[][] board = game.getBoardSnapshot();

		Piece piece =
				board[from.getRow()][from.getColumn()];

		if (piece == null) {

			selection.apply(move);

			clearHighlights();
			refresh();

			return;
		}

		Image img = PieceIcons.getIcon(piece);

		pendingMove = move;

		animFromR = from.getRow();
		animFromC = from.getColumn();

		animToR = to.getRow();
		animToC = to.getColumn();

		animateMove(
				animFromR,
				animFromC,
				animToR,
				animToC,
				img
				);
	}

	private void animateMove(
			int fromR,
			int fromC,
			int toR,
			int toC,
			Image pieceImg
			) {

		int square = getWidth() / 8;

		animating = true;
		animPiece = pieceImg;

		startX = fromC * square;
		startY = (7 - fromR) * square;

		targetX = toC * square;
		targetY = (7 - toR) * square;

		animX = startX;
		animY = startY;

		animStartTime = System.currentTimeMillis();

		Timer timer = new Timer(10, e -> {

			long elapsed =
					System.currentTimeMillis()
					- animStartTime;

			float t =
					Math.min(
							1f,
							elapsed / (float) ANIM_DURATION
							);

			animX =
					(int) (startX + (targetX - startX) * t);

			animY =
					(int) (startY + (targetY - startY) * t);

			repaint();

			if (t >= 1f) {

				((Timer) e.getSource()).stop();

				animating = false;

				if (pendingMove != null) {

					selection.apply(pendingMove);

					pendingMove = null;
				}

				clearHighlights();
				refresh();

				if (game.isCheckmate()) {

					Color winner =
							game.getCurrentPlayer()
							.opposite();


					CheckmateDialog dialog =
							new CheckmateDialog(
									SwingUtilities.getWindowAncestor(this),
									winner
									);

					dialog.setVisible(true);

					return;
				}

				// L'AI gioca SOLO quando è il turno del Nero
				if (aiPlayer != null
				&& game.getCurrentPlayer() == Color.BLACK) {

					playAiMove();
				}
			}
		});

		timer.start();
	}

	private void playAiMove() {

		if (aiPlayer == null) {
			return;
		}

		if (animating) {
			return;
		}

		try {

			Move aiMove =
					aiPlayer.chooseMove(game);

			startAnimatedMove(
					aiMove.getFrom(),
					aiMove.getTo(),
					aiMove
					);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isAnimatingFrom(int r, int c) {
		return animating
				&& r == animFromR
				&& c == animFromC;
	}

	public boolean isAnimating() {
		return animating;
	}

	@Override
	protected void paintChildren(Graphics g) {

		super.paintChildren(g);

		if (animating && animPiece != null) {

			int square = getWidth() / 8;

			g.drawImage(
					animPiece,
					animX,
					animY,
					square,
					square,
					null
					);
		}
	}

	public void clearHighlights() {

		for (SquarePanel[] row : squares) {
			for (SquarePanel sq : row) {

				sq.setHighlighted(false);
				sq.setSelectedSquare(false);
			}
		}

		repaint();
	}

	public void highlightSelected(int r, int c) {
		squares[r][c].setSelectedSquare(true);
	}

	public void highlightMoves(List<Move> moves) {

		for (Move move : moves) {

			int r = move.getTo().getRow();
			int c = move.getTo().getColumn();

			squares[r][c].setHighlighted(true);
		}

		repaint();
	}

	public void highlightKingInCheck() {

		for (SquarePanel[] row : squares) {
			for (SquarePanel sq : row) {
				sq.setKingInCheck(false);
			}
		}

		Piece[][] board = game.getBoardSnapshot();
		Color player = game.getCurrentPlayer();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {

				Piece piece = board[r][c];

				if (piece instanceof King
						&& piece.getColor() == player) {

					squares[r][c].setKingInCheck(true);
					return;
				}
			}
		}
	}

	public void refresh() {

		for (SquarePanel[] row : squares) {
			for (SquarePanel sq : row) {

				sq.setHighlighted(false);
				sq.setSelectedSquare(false);
			}
		}

		for (SquarePanel[] row : squares) {
			for (SquarePanel sq : row) {
				sq.refresh();
			}
		}

		if (game.isInCheck()) {

			highlightKingInCheck();

		} else {

			for (SquarePanel[] row : squares) {
				for (SquarePanel sq : row) {
					sq.setKingInCheck(false);
				}
			}
		}

		repaint();
	}
}