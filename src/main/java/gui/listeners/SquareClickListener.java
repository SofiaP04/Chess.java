package gui.listeners;

import beans.move.Move;
import beans.state.Coordinate;
import core.facade.GameFacade;
import core.facade.MoveSelectionFacade;
import gui.components.BoardPanel;
import gui.dialogs.PromotionDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SquareClickListener extends MouseAdapter {

	private final int row;
	private final int col;

	private final GameFacade game;
	private final MoveSelectionFacade selection;
	private final BoardPanel parent;

	public SquareClickListener(
			int row,
			int col,
			GameFacade game,
			MoveSelectionFacade selection,
			BoardPanel parent
			) {
		this.row = row;
		this.col = col;
		this.game = game;
		this.selection = selection;
		this.parent = parent;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		Coordinate clicked =
				new Coordinate(
						row,
						col
						);

		if (selection.getSelected() == null) {

			List<Move> moves =
					selection.select(clicked);

			parent.clearHighlights();

			if (!moves.isEmpty()) {

				parent.highlightSelected(
						row,
						col
						);

				parent.highlightMoves(
						moves
						);
			}

			return;
		}

		Coordinate from =
				selection.getSelected();

		List<Move> candidates =
				game.getMovesTo(
						from,
						clicked
						);

		if (candidates.isEmpty()) {

			parent.clearHighlights();
			selection.reset();

			return;
		}

		Move chosen;

		if (candidates.size() == 1) {

			chosen = candidates.get(0);

		} else {

			chosen =
					PromotionDialog
					.askPromotionMove(
							candidates
							);
		}

		parent.startAnimatedMove(
				from,
				clicked,
				chosen
				);
	}
}