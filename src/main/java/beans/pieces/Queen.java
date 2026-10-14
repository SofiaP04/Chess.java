package beans.pieces;

import beans.state.Color;
import core.rules.movement.QueenMovementPattern;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, new QueenMovementPattern());
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
