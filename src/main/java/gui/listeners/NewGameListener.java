package gui.listeners;

import javax.swing.JFrame;

import gui.menu.NewGameOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameListener implements ActionListener {

	private final JFrame parent;

	public NewGameListener(JFrame parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NewGameOption.show(parent);
	}
}