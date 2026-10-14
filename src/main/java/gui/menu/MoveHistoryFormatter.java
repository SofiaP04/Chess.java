package gui.menu;

import beans.move.Move;
import beans.state.Coordinate;

import java.util.List;

public final class MoveHistoryFormatter {

	private MoveHistoryFormatter() {
	}

	public static String format(List<Move> moves) {

		if (moves.isEmpty()) {
			return "No moves played yet.";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < moves.size(); i++) {

			if (i % 2 == 0) {
				sb.append(i / 2 + 1)
				.append(". ");
			}

			sb.append(toUci(moves.get(i)))
			.append(" ");

			if (i % 2 == 1) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private static String toUci(Move move) {

		return square(move.getFrom())
				+ square(move.getTo());
	}

	private static String square(Coordinate c) {

		char file = (char) ('a' + c.getColumn());
		int rank = c.getRow() + 1;

		return "" + file + rank;
	}
}