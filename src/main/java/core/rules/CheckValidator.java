package core.rules;

import beans.board.ChessBoard;
import beans.move.Move;
import beans.pieces.Piece;
import beans.pieces.King;
import beans.state.Color;
import beans.state.Coordinate;
import beans.state.GameState;
import core.move.MoveGenerator;

public class CheckValidator {

	private final MoveGenerator generator;

	public CheckValidator(MoveGenerator generator) {
		this.generator = generator;
	}

	public boolean leavesKingInCheck(GameState state, Move move) {

		// Copia dello stato
		GameState copyState = new GameState(state.getBoard().deepCopy(), state.getCurrentPlayer());

		// Applica la mossa sulla copia
		copyState.getBoard().applyMove(move);

		Color mover = move.getMovedPiece().getColor();
		Coordinate kingPos = findKing(copyState.getBoard(), mover);

		return isSquareAttacked(copyState, kingPos, mover.opposite());
	}

	public boolean isKingInCheck(ChessBoard board, Color player) {
		GameState tmp = new GameState(board, player);
		Coordinate kingPos = findKing(board, player);
		return isSquareAttacked(tmp, kingPos, player.opposite());
	}

	private Coordinate findKing(ChessBoard board, Color color) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Coordinate coord = new Coordinate(r, c);
				Piece p = board.getPieceAt(coord);
				if (p instanceof King && p.getColor() == color) {
					return coord;
				}
			}
		}
		throw new IllegalStateException("King not found");
	}

	private boolean isSquareAttacked(GameState state, Coordinate target, Color attacker) {

		ChessBoard board = state.getBoard();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {

				Coordinate from = new Coordinate(r, c);
				Piece p = board.getPieceAt(from);

				if (p == null || p.getColor() != attacker) continue;

				// solo mosse legali
				for (Move m : generator.generateMoves(state, from)) {
					if (m.getTo().equals(target)) {
						return true;
					}
				}
			}
		}

		return false;
	}
}