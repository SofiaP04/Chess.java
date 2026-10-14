package core.rules;

import beans.state.Coordinate;	
import beans.board.ChessBoard;
import beans.move.Move;
import beans.pieces.Pawn;
import beans.pieces.Piece;
import beans.state.GameState;
import beans.state.Color;

import java.util.ArrayList;
import java.util.List;

public class EnPassantRule implements Rule {

	@Override
	public List<Move> apply(GameState state, Piece piece, Coordinate from, List<Move> moves) {
		List<Move> result = new ArrayList<>(moves);

		if (!(piece instanceof Pawn pawn)) return result;

		Coordinate ep = state.getEnPassantTarget();
		if (ep == null) return result;

		Color color = pawn.getColor();
		int direction = (color == Color.WHITE) ? 1 : -1;

		// la casella en passant deve essere una diagonale avanti di 1
		if (ep.getRow() == from.getRow() + direction &&
				Math.abs(ep.getColumn() - from.getColumn()) == 1) {

			ChessBoard board = state.getBoard();
			Coordinate capturedPos = new Coordinate(from.getRow(), ep.getColumn());
			Piece capturedPawn = board.getPieceAt(capturedPos);

			if (capturedPawn instanceof Pawn &&
					capturedPawn.getColor() != color) {

				Move enPassant = Move.enPassant(from, ep, pawn, capturedPawn);
				result.add(enPassant);
			}
		}

		return result;
	}
}