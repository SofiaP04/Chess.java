package gui.listeners;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.windows.SinglePlayerChessWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinglePlayerListener implements ActionListener {

	private final JDialog dialog;
	private final JFrame parent;

	public SinglePlayerListener(
			JDialog dialog,
			JFrame parent
			) {
		this.dialog = dialog;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dialog.dispose();
		parent.dispose();

		SwingUtilities.invokeLater(
				SinglePlayerChessWindow::new
				);
	}
}