package core.rules;

import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Piece;
import beans.state.GameState;

import java.util.List;

public interface Rule {
	List<Move> apply(GameState state, Piece piece, Coordinate from, List<Move> candidateMoves);
}