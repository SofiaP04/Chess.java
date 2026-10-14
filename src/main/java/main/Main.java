package main;

import javax.swing.SwingUtilities;

import gui.windows.HomeWindow;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(
				new Runnable() {

					@Override
					public void run() {
						new HomeWindow();
					}
				}
				);
	}
}