package core.rules;

import beans.state.Coordinate;
import beans.move.Move;
import beans.pieces.Bishop;
import beans.pieces.Knight;
import beans.pieces.Pawn;
import beans.pieces.Piece;
import beans.pieces.Queen;
import beans.pieces.Rook;
import beans.state.GameState;
import beans.state.Color;

import java.util.ArrayList;
import java.util.List;

public class PromotionRule implements Rule {

	@Override
	public List<Move> apply(GameState state, Piece piece, Coordinate from, List<Move> moves) {
		if (!(piece instanceof Pawn pawn)) return moves;

		List<Move> result = new ArrayList<>();

		for (Move m : moves) {
			if (isPromotionMove(pawn, m)) {

				Coordinate to = m.getTo();
				Piece captured = state.getBoard().getPieceAt(to);
				Color color = pawn.getColor();

				result.add(Move.promotion(from, to, pawn, captured, new Queen(color)));
				result.add(Move.promotion(from, to, pawn, captured, new Rook(color)));
				result.add(Move.promotion(from, to, pawn, captured, new Bishop(color)));
				result.add(Move.promotion(from, to, pawn, captured, new Knight(color)));

			} else {
				result.add(m);
			}
		}

		return result;
	}

	private boolean isPromotionMove(Pawn pawn, Move move) {
		int targetRow = move.getTo().getRow();
		Color color = pawn.getColor();

		return (color == Color.WHITE && targetRow == 7) ||
				(color == Color.BLACK && targetRow == 0);
	}
}