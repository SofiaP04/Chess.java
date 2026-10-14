package gui.windows;

import core.ai.AiEngineConnector;
import core.ai.AiPlayer;

public class SinglePlayerChessWindow extends ChessWindow {

	private AiEngineConnector connector;
	private AiPlayer aiPlayer;

	public SinglePlayerChessWindow() {

		super();

		try {

			connector = new AiEngineConnector();
			connector.connect();

			aiPlayer = new AiPlayer(connector);

			boardPanel.setAiPlayer(aiPlayer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(true);
	}

	public AiPlayer getAiPlayer() {
		return aiPlayer;
	}

	@Override
	public boolean isSinglePlayer() {
		return true;
	}
}