package core.initialization;

import beans.board.ChessBoard;
import beans.state.Color;
import beans.state.GameState;
import core.rules.*;
import core.managers.GameManager;
import core.managers.MoveManager;
import core.move.MoveApplier;
import core.move.MoveGenerator;

import java.util.List;

public final class GameInitializer {

	private GameInitializer() {}

	public static GameManager createStandardGame() {

		ChessBoard board = new ChessBoard();
		BoardInitializer.initialize(board);

		GameState state = new GameState(board, Color.WHITE);

		// RuleChain SENZA CheckRule
		RuleChain baseChain = new RuleChain(List.of(
				new MoveLegalityRule(),
				new EnPassantRule(),
				new CastlingRule(),
				new PromotionRule()
				));

		// MoveGenerator preliminare
		MoveGenerator baseGenerator = new MoveGenerator(baseChain);

		// CheckValidator che usa il MoveGenerator
		CheckValidator validator = new CheckValidator(baseGenerator);

		// RuleChain COMPLETA
		RuleChain fullChain = new RuleChain(List.of(
				new MoveLegalityRule(),
				new EnPassantRule(),
				new CastlingRule(),
				new PromotionRule(),
				new CheckRule(validator)  
				));

		// MoveGenerator finale
		MoveGenerator finalGenerator = new MoveGenerator(fullChain);

		MoveApplier applier = new MoveApplier();
		MoveManager moveManager = new MoveManager(state, finalGenerator, applier);


		return new GameManager(state, moveManager);
	}
}
