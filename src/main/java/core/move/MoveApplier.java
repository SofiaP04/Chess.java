package core.move;

import beans.board.ChessBoard;
import beans.move.Move;
import beans.pieces.King;
import beans.pieces.Pawn;
import beans.pieces.Piece;
import beans.pieces.Rook;
import beans.state.Color;
import beans.state.Coordinate;
import beans.state.GameState;

public class MoveApplier {

	public void applyMove(GameState state, Move move) {

		ChessBoard board = state.getBoard();
		Coordinate from = move.getFrom();
		Coordinate to = move.getTo();
		Piece moved = move.getMovedPiece();

		// 1. Gestione cattura (normale o en passant)
		if (move.isEnPassant()) {
			Coordinate capturedPawnPos = new Coordinate(from.getRow(), to.getColumn());
			board.setPieceAt(capturedPawnPos, null);
		} else if (move.getCapturedPiece() != null) {
			board.setPieceAt(to, null);
		}

		// 2. Spostamento del pezzo
		board.setPieceAt(from, null);
		board.setPieceAt(to, moved);

		// 3. Gestione promozione
		if (move.isPromotion()) {
			board.setPieceAt(to, move.getPromotionResult());
		}

		// 4. Gestione arrocco
		if (move.isCastling()) {
			applyCastlingRookMove(board, moved.getColor(), to);
		}

		// 5. Aggiornamento diritti di arrocco
		updateCastlingRights(state, moved, from);

		// 6. Aggiornamento en passant target
		updateEnPassantTarget(state, moved, from, to);

		// 7. Aggiornamento turno
		state.setCurrentPlayer(state.getCurrentPlayer().opposite());

		// 8. Aggiornamento cronologia
		state.getMoveHistory().add(move);
	}

	private void applyCastlingRookMove(ChessBoard board, Color color, Coordinate kingTo) {
		int row = (color == Color.WHITE) ? 0 : 7;

		if (kingTo.getColumn() == 6) { // arrocco corto
			board.setPieceAt(new Coordinate(row, 5), board.getPieceAt(new Coordinate(row, 7)));
			board.setPieceAt(new Coordinate(row, 7), null);
		} else { // arrocco lungo
			board.setPieceAt(new Coordinate(row, 3), board.getPieceAt(new Coordinate(row, 0)));
			board.setPieceAt(new Coordinate(row, 0), null);
		}
	}

	private void updateCastlingRights(GameState state, Piece moved, Coordinate from) {

		// Se muove il re → perde entrambi i diritti
		if (moved instanceof King) {
			state.disableCastling(moved.getColor(), true, true);
			return;
		}

		// Se muove una torre → perde il diritto corrispondente
		if (moved instanceof Rook) {
			int col = from.getColumn();

			// Torre sul lato di donna
			if (col == 0) {
				state.disableCastling(moved.getColor(), false, true); // disabilita queen-side
			}

			// Torre sul lato di re
			if (col == 7) {
				state.disableCastling(moved.getColor(), true, false); // disabilita king-side
			}
		}
	}


	private void updateEnPassantTarget(GameState state, Piece moved, Coordinate from, Coordinate to) {
		// Reset
		state.setEnPassantTarget(null);

		// Se un pedone avanza di due → imposta target
		if (moved instanceof Pawn && Math.abs(to.getRow() - from.getRow()) == 2) {
			int row = (from.getRow() + to.getRow()) / 2;
			state.setEnPassantTarget(new Coordinate(row, from.getColumn()));
		}
	}
}
