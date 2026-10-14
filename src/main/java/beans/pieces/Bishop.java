package beans.pieces;

import beans.state.Color;
import core.rules.movement.BishopMovementPattern;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color, new BishopMovementPattern());
	}

	@Override
	public String getSymbol() {
		return "B";
	}
}
