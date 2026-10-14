package gui.menu;

import core.facade.GameFacade;
import gui.listeners.HomeListener;
import gui.listeners.MovesHistoryListener;
import gui.listeners.NewGameListener;
import gui.theme.GuiStyle;

import javax.swing.*;
import javax.swing.border.Border;

public class MenuBar extends JMenuBar {

	public MenuBar(
			JFrame parent,
			GameFacade game
			) {

		setBackground(
				GuiStyle.menuColor()
				);

		Border border =
				BorderFactory.createMatteBorder(
						0,
						0,
						1,
						0,
						GuiStyle.menuBorderColor()
						);

		setBorder(border);

		JMenu menu =
				new JMenu("☰ Menu");

		menu.setFont(
				GuiStyle.menuFont()
				);

		menu.setForeground(
				GuiStyle.textColor()
				);

		JMenuItem newGameItem =
				new JMenuItem("New Game");

		JMenuItem homeItem =
				new JMenuItem("Home");

		JMenuItem historyItem =
				new JMenuItem("Move History");

		newGameItem.setFont(
				GuiStyle.menuFont()
				);

		homeItem.setFont(
				GuiStyle.menuFont()
				);

		historyItem.setFont(
				GuiStyle.menuFont()
				);


		menu.add(newGameItem);
		menu.add(homeItem);
		menu.addSeparator();
		menu.add(historyItem);


		add(menu);

		UIManager.put(
				"Menu.selectionBackground",
				GuiStyle.menuHoverColor()
				);

		UIManager.put(
				"Menu.selectionForeground",
				GuiStyle.buttonTextColor()
				);

		UIManager.put(
				"MenuItem.selectionBackground",
				GuiStyle.menuHoverColor()
				);

		UIManager.put(
				"MenuItem.selectionForeground",
				GuiStyle.buttonTextColor()
				);

		homeItem.addActionListener(
				new HomeListener(parent)
				);

		newGameItem.addActionListener(
				new NewGameListener(parent)
				);

		historyItem.addActionListener(
				new MovesHistoryListener(parent, game)
				);

	}
}