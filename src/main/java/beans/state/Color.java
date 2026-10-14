package beans.state;

public enum Color {
	WHITE,
	BLACK;

	public Color opposite() {
		Color result;

		if (this == WHITE) {
			result = BLACK;
		} else {
			result = WHITE;
		}

		return result;
	}
}