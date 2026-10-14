package beans.state;

import java.util.Objects;

public class Coordinate {

	private final int row;      // 0–7
	private final int column;   // 0–7

	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Coordinate c)) return false;
		return row == c.row && column == c.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}

