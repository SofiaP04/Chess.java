package gui.windows;

public class PvPChessWindow extends ChessWindow {

	public PvPChessWindow() {
		super();
		setVisible(true);
	}

	@Override
	public boolean isSinglePlayer() {
		return false;
	}


}