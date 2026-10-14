package gui.listeners;

import core.facade.GameFacade;
import gui.menu.MovesHistoryOption;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovesHistoryListener
implements ActionListener {

	private final JFrame parent;
	private final GameFacade game;

	public MovesHistoryListener(
			JFrame parent,
			GameFacade game
			) {
		this.parent = parent;
		this.game = game;
	}

	@Override
	public void actionPerformed(
			ActionEvent e
			) {
		MovesHistoryOption.show(
				parent,
				game
				);
	}
}