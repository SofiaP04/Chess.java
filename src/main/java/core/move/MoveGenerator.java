package core.move;

import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Piece;
import beans.state.GameState;
import core.rules.RuleChain;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {

	private final RuleChain ruleChain;

	public MoveGenerator(RuleChain ruleChain) {
		this.ruleChain = ruleChain;
	}

	public List<Move> generateMoves(
			GameState state,
			Coordinate from
			) {

		Piece piece =
				state.getBoard().getPieceAt(from);

		if (piece == null) {
			return List.of();
		}

		List<Coordinate> geometry =
				piece.getMovementPattern()
				.generateGeometry(from);

		List<Move> geometricMoves =
				new ArrayList<>();

		for (Coordinate destination : geometry) {

			geometricMoves.add(
					Move.normal(
							from,
							destination,
							piece
							)
					);
		}

		return ruleChain.applyAll(
				state,
				piece,
				from,
				geometricMoves
				);
	}
}