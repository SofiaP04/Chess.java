package beans.pieces;

import beans.state.Color;
import core.rules.movement.KnightMovementPattern;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color, new KnightMovementPattern());
	}

	@Override
	public String getSymbol() {
		return "N";
	}
}
