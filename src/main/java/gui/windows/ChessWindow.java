package gui.windows;

import core.ai.AiPlayer;
import core.facade.GameFacade;
import core.facade.MoveSelectionFacade;
import core.initialization.GameInitializer;
import core.managers.GameManager;
import gui.components.AppIcon;
import gui.components.BoardPanel;
import gui.components.ChessBoardContainer;
import gui.menu.MenuBar;
import gui.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;

public abstract class ChessWindow extends JFrame {

	protected final GameManager engine;
	protected final GameFacade game;
	protected final MoveSelectionFacade selection;
	protected final BoardPanel boardPanel;

	private final Image background;

	protected ChessWindow() {

		setTitle("Chess.java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String bgPath = ThemeManager.get().get("bgmar");

		background = new ImageIcon(bgPath).getImage();

		JPanel bgPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);

				g.drawImage(
						background,
						0,
						0,
						getWidth(),
						getHeight(),
						null
						);
			}
		};

		bgPanel.setLayout(new BorderLayout());
		setContentPane(bgPanel);

		engine = GameInitializer.createStandardGame();

		game = new GameFacade(engine);

		selection = new MoveSelectionFacade(game);

		boardPanel = new BoardPanel(
				game,
				selection,
				null
				);

		ChessBoardContainer container =
				new ChessBoardContainer(boardPanel);

		container.setOpaque(false);

		bgPanel.add(
				container,
				BorderLayout.CENTER
				);

		setJMenuBar(
				new MenuBar(
						this,
						game
						)
				);

		setSize(900, 900);
		setLocationRelativeTo(null);

		setIconImage(
				AppIcon.get().getImage()
				);
	}

	public abstract boolean isSinglePlayer();
}