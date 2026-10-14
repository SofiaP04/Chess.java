package core.facade;

import beans.state.Coordinate;
import beans.move.Move;

import java.util.List;

public class MoveSelectionFacade {

	private final GameFacade game;
	private Coordinate selected;

	public MoveSelectionFacade(GameFacade game) {
		this.game = game;
	}

	public Coordinate getSelected() {
		return selected;
	}

	public List<Move> select(Coordinate c) {
		if (!game.isCurrentPlayerPiece(c)) {
			selected = null;
			return List.of();
		}
		selected = c;
		return game.getLegalMoves(c);
	}

	public void apply(Move move) {
		game.applyMove(move);
		selected = null;
	}

	public void reset() {
		selected = null;
	}
}
