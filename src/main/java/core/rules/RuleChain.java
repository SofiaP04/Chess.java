package core.rules;

import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Piece;
import beans.state.GameState;

import java.util.List;

public class RuleChain {

	private final List<Rule> rules;

	public RuleChain(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Move> applyAll(GameState state, Piece piece, Coordinate from, List<Move> moves) {
		List<Move> current = moves;

		for (Rule rule : rules) {
			current = rule.apply(state, piece, from, current);
		}

		return current;
	}
}
