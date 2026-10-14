package core.rules;

import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Piece;
import beans.state.GameState;
import java.util.ArrayList;
import java.util.List;

public class CheckRule implements Rule {

	private final CheckValidator validator;

	public CheckRule(CheckValidator validator) {
		this.validator = validator;
	}

	@Override
	public List<Move> apply(GameState state,
			Piece piece,
			Coordinate from,
			List<Move> moves) {

		List<Move> result = new ArrayList<>();

		for (Move m : moves) {
			if (!validator.leavesKingInCheck(state, m)) {
				result.add(m);
			}
		}

		return result;
	}
}
