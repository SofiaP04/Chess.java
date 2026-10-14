package core.rules.movement;

import beans.state.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class KingMovementPattern implements MovementPattern {

	private static final int[][] DIR = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1},
			{-1, -1}, {-1, 1}, {1, -1}, {1, 1}
	};

	@Override
	public List<Coordinate> generateGeometry(Coordinate from) {
		List<Coordinate> coords = new ArrayList<>();

		for (int[] d : DIR) {
			coords.add(new Coordinate(
					from.getRow() + d[0],
					from.getColumn() + d[1]
					));
		}

		return coords;
	}
}
