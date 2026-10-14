package beans.state;

import beans.board.ChessBoard;
import beans.move.Move;

import java.util.ArrayList;
import java.util.List;

public class GameState {

	private ChessBoard board;
	private Color currentPlayer;

	// Diritti di arrocco
	private boolean whiteCanCastleKingSide = true;
	private boolean whiteCanCastleQueenSide = true;
	private boolean blackCanCastleKingSide = true;
	private boolean blackCanCastleQueenSide = true;

	// En passant target (casella catturabile)
	private Coordinate enPassantTarget;

	// Cronologia delle mosse
	private final List<Move> moveHistory = new ArrayList<>();

	public GameState(ChessBoard board, Color startingPlayer) {
		this.board = board;
		this.currentPlayer = startingPlayer;
	}

	// --- GETTER E SETTER ---

	public ChessBoard getBoard() {
		return board;
	}

	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean canCastleKingSide(Color color) {
		return (color == Color.WHITE) ? whiteCanCastleKingSide : blackCanCastleKingSide;
	}

	public boolean canCastleQueenSide(Color color) {
		return (color == Color.WHITE) ? whiteCanCastleQueenSide : blackCanCastleQueenSide;
	}

	public void disableCastling(Color color, boolean kingSide, boolean queenSide) {
		if (color == Color.WHITE) {
			if (kingSide) whiteCanCastleKingSide = false;
			if (queenSide) whiteCanCastleQueenSide = false;
		} else {
			if (kingSide) blackCanCastleKingSide = false;
			if (queenSide) blackCanCastleQueenSide = false;
		}
	}

	public Coordinate getEnPassantTarget() {
		return enPassantTarget;
	}

	public void setEnPassantTarget(Coordinate enPassantTarget) {
		this.enPassantTarget = enPassantTarget;
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}
}
