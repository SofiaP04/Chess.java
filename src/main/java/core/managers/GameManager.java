package core.managers;

import beans.state.Color;
import beans.state.Coordinate;
import beans.board.ChessBoard;
import beans.move.Move;
import beans.state.GameState;
import core.move.MoveGenerator;

import java.util.List;

public class GameManager {

	private final GameState state;
	private final MoveManager moveManager;

	public GameManager(GameState state, MoveManager moveManager) {
		this.state = state;
		this.moveManager = moveManager;
	}

	public List<Move> getLegalMoves(Coordinate from) {
		return moveManager.getLegalMoves(from);
	}

	public void applyMove(Move move) {
		moveManager.applyMove(move);
	}

	public GameState getState() {
		return state;
	}

	public ChessBoard getBoard() {
		return state.getBoard();
	}

	public Color getCurrentPlayer() {
		return state.getCurrentPlayer();
	}

	public MoveGenerator getMoveGenerator() {
		return moveManager.getGenerator();
	}
}
