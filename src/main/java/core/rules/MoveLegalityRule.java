package core.rules;

import beans.state.Coordinate;
import beans.state.Color;
import beans.board.ChessBoard;
import beans.move.Move;
import beans.pieces.Piece;
import beans.pieces.Queen;
import beans.pieces.Rook;
import beans.pieces.Bishop;
import beans.pieces.Pawn;
import beans.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class MoveLegalityRule implements Rule {

	@Override
	public List<Move> apply(GameState state, Piece piece, Coordinate from, List<Move> moves) {
		ChessBoard board = state.getBoard();
		List<Move> result = new ArrayList<>();

		for (Move m : moves) {
			Coordinate to = m.getTo();

			if (!inside(to)) continue;

			// 🔹 Caso speciale: pedone
			if (piece instanceof Pawn) {
				if (isLegalPawnMove(state, (Pawn) piece, from, to)) {
					result.add(m);
				}
				continue; // salta la logica generica
			}

			// 🔹 Logica generica per gli altri pezzi
			Piece target = board.getPieceAt(to);

			if (target != null && target.getColor() == piece.getColor()) continue;

			if (isSliding(piece) && blocked(board, from, to)) continue;

			result.add(m);
		}

		return result;
	}

	private boolean inside(Coordinate c) {
		return c.getRow() >= 0 && c.getRow() < 8 &&
				c.getColumn() >= 0 && c.getColumn() < 8;
	}

	private boolean isSliding(Piece p) {
		return p instanceof Rook || p instanceof Bishop || p instanceof Queen;
	}

	private boolean blocked(ChessBoard board, Coordinate from, Coordinate to) {
		int dr = Integer.compare(to.getRow(), from.getRow());
		int dc = Integer.compare(to.getColumn(), from.getColumn());

		int r = from.getRow() + dr;
		int c = from.getColumn() + dc;

		while (r != to.getRow() || c != to.getColumn()) {
			if (board.getPieceAt(new Coordinate(r, c)) != null) return true;
			r += dr;
			c += dc;
		}

		return false;
	}

	// 🔹 Logica completa del pedone (senza en passant, che gestisci già con EnPassantRule)
	private boolean isLegalPawnMove(GameState state, Pawn pawn, Coordinate from, Coordinate to) {
		ChessBoard board = state.getBoard();

		int dr = to.getRow() - from.getRow();
		int dc = to.getColumn() - from.getColumn();

		Color color = pawn.getColor();
		int dir = (color == Color.WHITE) ? 1 : -1;      // bianco verso row+1, nero verso row-1
		int startRow = (color == Color.WHITE) ? 1 : 6;  // con la tua convenzione di righe

		Piece target = board.getPieceAt(to);

		// Movimento in avanti (stessa colonna)
		if (dc == 0) {
			// passo singolo
			if (dr == dir) {
				return target == null;
			}
			// doppio passo dalla riga iniziale
			if (dr == 2 * dir && from.getRow() == startRow) {
				Coordinate mid = new Coordinate(from.getRow() + dir, from.getColumn());
				return target == null && board.getPieceAt(mid) == null;
			}
			return false;
		}

		// Cattura diagonale
		if (Math.abs(dc) == 1 && dr == dir) {
			// cattura normale: deve esserci un pezzo avversario
			return target != null && target.getColor() != color;
		}

		// tutto il resto non è una mossa di pedone
		return false;
	}
}
