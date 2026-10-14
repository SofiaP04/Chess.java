package core.move;

import beans.move.Move;
import beans.state.Coordinate;
import beans.state.GameState;

public class MoveHistoryBuilder {

	public String build(GameState state) {

		StringBuilder builder =
				new StringBuilder(
						"position startpos"
						);

		if (!state.getMoveHistory().isEmpty()) {
			builder.append(" moves");
		}

		for (Move move : state.getMoveHistory()) {

			builder.append(" ")
			.append(toUci(move));
		}

		return builder.toString();
	}

	private String toUci(Move move) {

		return square(move.getFrom())
				+ square(move.getTo());
	}

	private String square(Coordinate c) {

		char file =
				(char) ('a' + c.getColumn());

		int rank =
				c.getRow() + 1;

		return "" + file + rank;
	}
}