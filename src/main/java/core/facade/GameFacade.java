package core.facade;

import beans.state.Color;
import beans.state.Coordinate;
import beans.state.GameState;
import beans.move.Move;
import beans.pieces.Piece;
import core.evaluation.GameStatusEvaluator;
import core.managers.GameManager;
import core.rules.CheckValidator;

import java.util.ArrayList;
import java.util.List;

public class GameFacade {

	private final GameManager manager;
	private final GameStatusEvaluator evaluator;

	public GameFacade(GameManager manager) {

		this.manager = manager;

		CheckValidator validator =
				new CheckValidator(
						manager.getMoveGenerator()
						);

		this.evaluator =
				new GameStatusEvaluator(
						validator,
						manager.getMoveGenerator()
						);
	}

	public Color getCurrentPlayer() {
		return manager.getCurrentPlayer();
	}

	public Piece[][] getBoardSnapshot() {

		Piece[][] snapshot = new Piece[8][8];

		for (int row = 0; row < 8; row++) {

			for (int column = 0; column < 8; column++) {

				snapshot[row][column] =
						manager.getBoard().getPieceAt(
								new Coordinate(
										row,
										column
										)
								);
			}
		}

		return snapshot;
	}

	public boolean isCurrentPlayerPiece(
			Coordinate coordinate
			) {

		Piece piece =
				manager.getBoard()
				.getPieceAt(coordinate);

		return piece != null
				&& piece.getColor()
				== manager.getCurrentPlayer();
	}

	public boolean isInCheck() {
		return evaluator.isInCheck(
				manager.getState(),
				manager.getCurrentPlayer()
				);
	}

	public boolean isCheckmate() {
		return evaluator.isCheckmate(
				manager.getState(),
				manager.getCurrentPlayer()
				);
	}

	public boolean isStalemate() {
		return evaluator.isStalemate(
				manager.getState(),
				manager.getCurrentPlayer()
				);
	}

	public List<Move> getLegalMoves(
			Coordinate from
			) {
		return manager.getLegalMoves(from);
	}

	public List<Move> getMovesTo(
			Coordinate from,
			Coordinate to
			) {

		List<Move> result =
				new ArrayList<>();

		List<Move> legalMoves =
				manager.getLegalMoves(from);

		for (Move move : legalMoves) {

			if (move.getTo().equals(to)) {
				result.add(move);
			}
		}

		return result;
	}

	public List<Move> getPromotionMoves(
			Coordinate from,
			Coordinate to
			) {

		List<Move> result =
				new ArrayList<>();

		List<Move> legalMoves =
				manager.getLegalMoves(from);

		for (Move move : legalMoves) {

			if (move.getTo().equals(to)
					&& move.isPromotion()) {

				result.add(move);
			}
		}

		return result;
	}

	public void applyMove(
			Move move
			) {
		manager.applyMove(move);
	}

	public List<Move> getMoveHistory() {
		return manager.getState().getMoveHistory();
	}

	public GameState getState() {
		return manager.getState();
	}
}