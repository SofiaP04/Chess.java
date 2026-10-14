package beans.pieces;

import beans.state.Color;
import core.rules.movement.RookMovementPattern;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, new RookMovementPattern());
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
