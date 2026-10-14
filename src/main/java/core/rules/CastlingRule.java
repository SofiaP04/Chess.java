package core.rules;

import beans.state.Color;
import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.King;
import beans.pieces.Piece;
import beans.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class CastlingRule implements Rule {

	@Override
	public List<Move> apply(GameState state, Piece piece, Coordinate from, List<Move> moves) {
		List<Move> result = new ArrayList<>(moves);

		if (!(piece instanceof King king)) return result;

		Color color = king.getColor();
		int row = (color == Color.WHITE) ? 0 : 7;

		if (state.canCastleKingSide(color) && empty(state, row, 5) && empty(state, row, 6)) {
			result.add(Move.castling(from, new Coordinate(row, 6), king));
		}

		if (state.canCastleQueenSide(color) &&
				empty(state, row, 3) && empty(state, row, 2) && empty(state, row, 1)) {
			result.add(Move.castling(from, new Coordinate(row, 2), king));
		}

		return result;
	}

	private boolean empty(GameState s, int r, int c) {
		return s.getBoard().getPieceAt(new Coordinate(r, c)) == null;
	}
}