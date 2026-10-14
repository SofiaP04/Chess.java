package core.rules.movement;

import beans.state.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class KnightMovementPattern implements MovementPattern {

	private static final int[][] MOVES = {
			{-2, -1}, {-2, 1},
			{-1, -2}, {-1, 2},
			{1, -2},  {1, 2},
			{2, -1},  {2, 1}
	};

	@Override
	public List<Coordinate> generateGeometry(Coordinate from) {
		List<Coordinate> coords = new ArrayList<>();

		for (int[] m : MOVES) {
			coords.add(new Coordinate(from.getRow() + m[0], from.getColumn() + m[1]));
		}

		return coords;
	}
}
