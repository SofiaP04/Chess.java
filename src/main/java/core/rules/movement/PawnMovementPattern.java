package core.rules.movement;


import beans.state.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class PawnMovementPattern implements MovementPattern {

	@Override
	public List<Coordinate> generateGeometry(Coordinate from) {
		List<Coordinate> coords = new ArrayList<>();

		int row = from.getRow();
		int col = from.getColumn();

		// Geometria astratta: avanti, doppio, diagonali
		coords.add(new Coordinate(row + 1, col));
		coords.add(new Coordinate(row + 2, col));
		coords.add(new Coordinate(row + 1, col - 1));
		coords.add(new Coordinate(row + 1, col + 1));

		coords.add(new Coordinate(row - 1, col));
		coords.add(new Coordinate(row - 2, col));
		coords.add(new Coordinate(row - 1, col - 1));
		coords.add(new Coordinate(row - 1, col + 1));

		return coords;
	}
}
