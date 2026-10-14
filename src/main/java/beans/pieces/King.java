package beans.pieces;

import beans.state.Color;
import core.rules.movement.KingMovementPattern;

public class King extends Piece {

	public King(Color color) {
		super(color, new KingMovementPattern());
	}

	@Override
	public String getSymbol() {
		return "K";
	}
}
