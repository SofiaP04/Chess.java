package gui.listeners;

import javax.swing.*;

import gui.theme.ThemeLoader;
import gui.theme.ThemeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyThemeListener implements ActionListener {

	private final JFrame parent;
	private final String theme;

	public ApplyThemeListener(
			JFrame parent,
			String theme
			) {
		this.parent = parent;
		this.theme = theme;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ThemeManager.setTheme(
				ThemeLoader.load(theme)
				);

		JOptionPane.showMessageDialog(
				parent,
				"Theme applied: " + theme
				);

		parent.dispose();
	}
}