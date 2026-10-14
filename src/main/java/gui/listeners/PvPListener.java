package gui.listeners;

import javax.swing.*;

import gui.windows.PvPChessWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PvPListener
implements ActionListener {

	private final JDialog dialog;
	private final JFrame parent;

	public PvPListener(
			JDialog dialog,
			JFrame parent
			) {
		this.dialog = dialog;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(
			ActionEvent e
			) {

		dialog.dispose();
		parent.dispose();

		SwingUtilities.invokeLater(
				PvPChessWindow::new
				);
	}
}