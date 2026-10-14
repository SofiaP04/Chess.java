package core.rules.movement;

import beans.state.Coordinate;
import java.util.List;

public interface MovementPattern {
	List<Coordinate> generateGeometry(Coordinate from);
}
