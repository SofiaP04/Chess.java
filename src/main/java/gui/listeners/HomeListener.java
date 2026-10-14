package gui.listeners;

import javax.swing.*;

import gui.menu.HomeOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeListener
implements ActionListener {

	private final JFrame parent;

	public HomeListener(
			JFrame parent
			) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(
			ActionEvent e
			) {
		HomeOption.show(parent);
	}
}