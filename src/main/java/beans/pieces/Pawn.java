package beans.pieces;

import beans.state.Color;
import core.rules.movement.PawnMovementPattern;

public class Pawn extends Piece {

	public Pawn(Color color) {
		super(color, new PawnMovementPattern());
	}

	@Override
	public String getSymbol() {
		return "P";
	}
}
