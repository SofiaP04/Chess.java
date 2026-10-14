package beans.move;

import beans.state.Coordinate;
import beans.pieces.Piece;
import java.util.Objects;

public final class Move {

	private final Coordinate from;
	private final Coordinate to;

	private final Piece movedPiece;
	private final Piece capturedPiece;

	private final Piece promotionResult;
	private final boolean castling;
	private final boolean enPassant;

	private Move(Coordinate from,
			Coordinate to,
			Piece movedPiece,
			Piece capturedPiece,
			Piece promotionResult,
			boolean castling,
			boolean enPassant) {

		this.from = Objects.requireNonNull(from);
		this.to = Objects.requireNonNull(to);
		this.movedPiece = Objects.requireNonNull(movedPiece);
		this.capturedPiece = capturedPiece;
		this.promotionResult = promotionResult;
		this.castling = castling;
		this.enPassant = enPassant;
	}

	public static Move normal(Coordinate from, Coordinate to, Piece movedPiece) {
		return new Move(from, to, movedPiece, null, null, false, false);
	}

	public static Move capture(Coordinate from, Coordinate to, Piece movedPiece, Piece capturedPiece) {
		return new Move(from, to, movedPiece, capturedPiece, null, false, false);
	}

	public static Move promotion(Coordinate from,
			Coordinate to,
			Piece movedPiece,
			Piece capturedPiece,
			Piece promotionResult) {
		return new Move(from, to, movedPiece, capturedPiece, Objects.requireNonNull(promotionResult), false, false);
	}

	public static Move castling(Coordinate from, Coordinate to, Piece king) {
		return new Move(from, to, king, null, null, true, false);
	}

	public static Move enPassant(Coordinate from,
			Coordinate to,
			Piece movedPawn,
			Piece capturedPawn) {
		return new Move(from, to, movedPawn, capturedPawn, null, false, true);
	}

	public Coordinate getFrom() {
		return from;
	}

	public Coordinate getTo() {
		return to;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public Piece getPromotionResult() {
		return promotionResult;
	}

	public boolean isPromotion() {
		return promotionResult != null;
	}

	public boolean isCastling() {
		return castling;
	}

	public boolean isEnPassant() {
		return enPassant;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Move)) return false;
		Move move = (Move) o;
		return castling == move.castling &&
				enPassant == move.enPassant &&
				Objects.equals(from, move.from) &&
				Objects.equals(to, move.to) &&
				Objects.equals(movedPiece, move.movedPiece) &&
				Objects.equals(capturedPiece, move.capturedPiece) &&
				Objects.equals(promotionResult, move.promotionResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to, movedPiece, capturedPiece, promotionResult, castling, enPassant);
	}

	@Override
	public String toString() {
		return "Move{" +
				"from=" + from +
				", to=" + to +
				", movedPiece=" + movedPiece +
				", capturedPiece=" + capturedPiece +
				", promotionResult=" + promotionResult +
				", castling=" + castling +
				", enPassant=" + enPassant +
				'}';
	}
}
