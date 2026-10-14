package core.initialization;

import beans.board.ChessBoard;
import beans.pieces.*;
import beans.state.Color;
import beans.state.Coordinate;

public class BoardInitializer {

	public static void initialize(ChessBoard board) {
		placePawns(board);
		placeRooks(board);
		placeKnights(board);
		placeBishops(board);
		placeQueens(board);
		placeKings(board);
	}

	private static void placePawns(ChessBoard board) {
		for (int col = 0; col < 8; col++) {
			board.setPieceAt(new Coordinate(1, col), new Pawn(Color.WHITE));
			board.setPieceAt(new Coordinate(6, col), new Pawn(Color.BLACK));
		}
	}

	private static void placeRooks(ChessBoard board) {
		board.setPieceAt(new Coordinate(0, 0), new Rook(Color.WHITE));
		board.setPieceAt(new Coordinate(0, 7), new Rook(Color.WHITE));
		board.setPieceAt(new Coordinate(7, 0), new Rook(Color.BLACK));
		board.setPieceAt(new Coordinate(7, 7), new Rook(Color.BLACK));
	}

	private static void placeKnights(ChessBoard board) {
		board.setPieceAt(new Coordinate(0, 1), new Knight(Color.WHITE));
		board.setPieceAt(new Coordinate(0, 6), new Knight(Color.WHITE));
		board.setPieceAt(new Coordinate(7, 1), new Knight(Color.BLACK));
		board.setPieceAt(new Coordinate(7, 6), new Knight(Color.BLACK));
	}

	private static void placeBishops(ChessBoard board) {
		board.setPieceAt(new Coordinate(0, 2), new Bishop(Color.WHITE));
		board.setPieceAt(new Coordinate(0, 5), new Bishop(Color.WHITE));
		board.setPieceAt(new Coordinate(7, 2), new Bishop(Color.BLACK));
		board.setPieceAt(new Coordinate(7, 5), new Bishop(Color.BLACK));
	}

	private static void placeQueens(ChessBoard board) {
		board.setPieceAt(new Coordinate(0, 3), new Queen(Color.WHITE));
		board.setPieceAt(new Coordinate(7, 3), new Queen(Color.BLACK));
	}

	private static void placeKings(ChessBoard board) {
		board.setPieceAt(new Coordinate(0, 4), new King(Color.WHITE));
		board.setPieceAt(new Coordinate(7, 4), new King(Color.BLACK));
	}
}
