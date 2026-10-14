package core.managers;

import beans.state.Coordinate;
import beans.move.Move;
import beans.state.GameState;
import core.move.MoveApplier;
import core.move.MoveGenerator;

import java.util.List;

public class MoveManager {

	private final GameState state;
	private final MoveGenerator generator;
	private final MoveApplier applier;

	public MoveManager(GameState state, MoveGenerator generator, MoveApplier applier) {
		this.state = state;
		this.generator = generator;
		this.applier = applier;
	}

	public MoveGenerator getGenerator() {
		return generator;
	}

	public List<Move> getLegalMoves(Coordinate from) {
		return generator.generateMoves(state, from);
	}

	public void applyMove(Move move) {
		applier.applyMove(state, move);
	}
}
