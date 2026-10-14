package core.rules.movement;

import beans.state.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class BishopMovementPattern implements MovementPattern {

	private static final int[][] DIR = {
			{-1, -1}, {-1, 1},
			{1, -1},  {1, 1}
	};

	@Override
	public List<Coordinate> generateGeometry(Coordinate from) {
		List<Coordinate> coords = new ArrayList<>();

		for (int[] d : DIR) {
			for (int i = 1; i < 8; i++) {
				coords.add(new Coordinate(
						from.getRow() + d[0] * i,
						from.getColumn() + d[1] * i
						));
			}
		}

		return coords;
	}
}
