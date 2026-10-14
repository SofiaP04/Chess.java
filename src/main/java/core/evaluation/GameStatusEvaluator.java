package core.evaluation;

import beans.state.Color;
import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Piece;
import beans.state.GameState;
import core.move.MoveGenerator;
import core.rules.CheckValidator;

import java.util.List;

public class GameStatusEvaluator {

	private final CheckValidator checkValidator;
	private final MoveGenerator generator;

	public GameStatusEvaluator(CheckValidator checkValidator, MoveGenerator generator) {
		this.checkValidator = checkValidator;
		this.generator = generator;
	}

	public boolean isInCheck(GameState state, Color player) {
		return checkValidator.isKingInCheck(state.getBoard(), player);
	}

	public boolean isCheckmate(GameState state, Color player) {
		if (!isInCheck(state, player)) return false;

		// se il giocatore ha almeno una mossa legale → non è scacco matto
		return !hasLegalMoves(state, player);
	}

	public boolean isStalemate(GameState state, Color player) {
		if (isInCheck(state, player)) return false;

		return !hasLegalMoves(state, player);
	}

	private boolean hasLegalMoves(GameState state, Color player) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {

				Coordinate from = new Coordinate(r, c);
				Piece p = state.getBoard().getPieceAt(from);

				if (p == null || p.getColor() != player) continue;

				List<Move> moves = generator.generateMoves(state, from);
				if (!moves.isEmpty()) return true;
			}
		}
		return false;
	}
}
