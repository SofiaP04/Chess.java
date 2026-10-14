package beans.board;

import beans.state.Color;
import beans.state.Coordinate;
import beans.state.SquareColor;
import beans.move.Move;
import beans.pieces.Piece;
import core.rules.CheckValidator;

public class ChessBoard {

	private final Square[][] grid = new Square[8][8];

	public ChessBoard() {
		initializeSquares();
	}

	private void initializeSquares() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {

				Coordinate coordinate = new Coordinate(row, col);

				boolean isLightSquare = ((row + col) % 2 == 0);
				SquareColor color = isLightSquare ? SquareColor.LIGHT : SquareColor.DARK;

				grid[row][col] = new Square(coordinate, color);
			}
		}
	}

	public Square getSquare(Coordinate coordinate) {
		return grid[coordinate.getRow()][coordinate.getColumn()];
	}

	public Piece getPieceAt(Coordinate coordinate) {
		return getSquare(coordinate).getPiece();
	}

	public void setPieceAt(Coordinate coordinate, Piece piece) {
		getSquare(coordinate).setPiece(piece);
	}

	public ChessBoard deepCopy() {
		ChessBoard copy = new ChessBoard();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Coordinate coord = new Coordinate(r, c);
				Piece piece = this.getPieceAt(coord);
				copy.setPieceAt(coord, piece);
			}
		}
		return copy;
	}
	public void applyMove(Move move) {
		// Rimuove il pezzo dalla posizione di partenza
		setPieceAt(move.getFrom(), null);

		// cattura normale
		if (move.getCapturedPiece() != null && !move.isEnPassant()) {
			setPieceAt(move.getTo(), null);
		}

		// en passant
		if (move.isEnPassant()) {
			Coordinate capturedPos = new Coordinate(
					move.getFrom().getRow(),
					move.getTo().getColumn()
					);
			setPieceAt(capturedPos, null);
		}

		// promozione
		if (move.isPromotion()) {
			setPieceAt(move.getTo(), move.getPromotionResult());
			return;
		}

		// arrocco (solo spostamento del re, la torre la muove GameState)
		if (move.isCastling()) {
			setPieceAt(move.getTo(), move.getMovedPiece());
			return;
		}

		// mossa normale
		setPieceAt(move.getTo(), move.getMovedPiece());
	}

}