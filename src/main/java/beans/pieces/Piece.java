package beans.pieces;

import beans.state.Color;
import core.rules.movement.MovementPattern;

public abstract class Piece {

	private final Color color;
	private final MovementPattern movementPattern;

	protected Piece(Color color, MovementPattern movementPattern) {
		this.color = color;
		this.movementPattern = movementPattern;
	}

	public Color getColor() {
		return color;
	}

	public MovementPattern getMovementPattern() {
		return movementPattern;
	}

	public abstract String getSymbol();
}
