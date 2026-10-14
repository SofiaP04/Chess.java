package core.ai;

import beans.move.Move;
import core.facade.GameFacade;
import core.move.MoveHistoryBuilder;

import java.io.IOException;
import java.util.List;

public class AiPlayer {

	private final AiEngineConnector connector;
	private final MoveHistoryBuilder historyBuilder;

	public AiPlayer(AiEngineConnector connector) {
		this.connector = connector;
		this.historyBuilder = new MoveHistoryBuilder();
	}

	public Move chooseMove(GameFacade game)
			throws IOException {

		String positionCommand =
				historyBuilder.build(
						game.getState()
						);

		connector.sendCommand(positionCommand);

		// è una difficoltà intermedia
		connector.sendCommand("go depth 8");

		String output =
				connector.readUntil("bestmove");

		String bestMoveLine = output.lines()
				.filter(line -> line.startsWith("bestmove"))
				.findFirst()
				.orElseThrow(() ->
				new IllegalStateException(
						"No bestmove returned by engine."
						));

		String uciMove =
				bestMoveLine.split("\\s+")[1];

		AiMove aiMove =
				AiMove.fromUci(uciMove);

		List<Move> candidates =
				game.getMovesTo(
						aiMove.getFrom(),
						aiMove.getTo()
						);

		if (candidates.isEmpty()) {
			throw new IllegalStateException(
					"Engine suggested an illegal move: "
							+ uciMove
					);
		}

		return candidates.get(0);
	}
}