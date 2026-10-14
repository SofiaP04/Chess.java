package core.ai;

import beans.state.Coordinate;

public class AiMove {

	private final Coordinate from;
	private final Coordinate to;

	public AiMove(Coordinate from, Coordinate to) {
		this.from = from;
		this.to = to;
	}

	public Coordinate getFrom() {
		return from;
	}

	public Coordinate getTo() {
		return to;
	}

	public static AiMove fromUci(String uci) {

		String fromSquare = uci.substring(0, 2);
		String toSquare = uci.substring(2, 4);

		Coordinate from = parseSquare(fromSquare);
		Coordinate to = parseSquare(toSquare);

		return new AiMove(from, to);
	}

	private static Coordinate parseSquare(String square) {

		int column = square.charAt(0) - 'a';
		int row = Character.getNumericValue(square.charAt(1)) - 1;

		return new Coordinate(row, column);
	}
}