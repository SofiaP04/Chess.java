package beans.board;

import beans.pieces.Piece;
import beans.state.Coordinate;
import beans.state.SquareColor;

public class Square {

	private final Coordinate coordinate;
	private final SquareColor color;
	private Piece piece;

	public Square(Coordinate coordinate, SquareColor color) {
		this.coordinate = coordinate;
		this.color = color;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public SquareColor getColor() {
		return color;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		return "Square{" +
				"coordinate=" + coordinate +
				", color=" + color +
				", piece=" + piece +
				'}';
	}
}
