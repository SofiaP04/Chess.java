package gui.windows;

import java.awt.event.ComponentAdapter;

import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.AppIcon;
import gui.components.CustomButton;
import gui.components.ScaledImagePanel;
import gui.dialogs.NewGameDialog;
import gui.theme.GuiStyle;
import gui.theme.ThemeLoader;
import gui.theme.ThemeManager;

import java.awt.*;;

public class HomeWindow extends JFrame {

	public HomeWindow() {

		setTitle("Chess.java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setMinimumSize(new Dimension(800, 500));
		setLocationRelativeTo(null);

		setIconImage(
				AppIcon.get().getImage()
				);

		ThemeManager.setTheme(
				ThemeLoader.load("Classic")
				);

		JPanel rootPanel = new JPanel(new BorderLayout());
		rootPanel.setBackground(GuiStyle.backgroundColor());
		rootPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

		JLabel titleLabel = new JLabel("Chess.java");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
		titleLabel.setForeground(GuiStyle.textColor());

		Font baseFont = GuiStyle.titleFont(60);
		titleLabel.setFont(baseFont);

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				float size = Math.max(
						32f,
						getWidth() * 0.055f
						);

				titleLabel.setFont(
						baseFont.deriveFont(size)
						);
			}
		});

		rootPanel.add(titleLabel, BorderLayout.NORTH);

		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setBackground(GuiStyle.backgroundColor());

		CustomButton newGameBtn =
				new CustomButton("New Game");

		newGameBtn.addActionListener(e ->
		NewGameDialog.show(this)
				);
		CustomButton themesBtn =
				new CustomButton("Themes");

		themesBtn.addActionListener(
				e -> new ThemeSelectionWindow().setVisible(true)
				);

		JPanel buttonsPanel = new JPanel();

		buttonsPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		buttonsPanel.setLayout(
				new BoxLayout(
						buttonsPanel,
						BoxLayout.Y_AXIS
						)
				);

		newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		themesBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		buttonsPanel.add(newGameBtn);
		buttonsPanel.add(Box.createVerticalStrut(35));
		buttonsPanel.add(themesBtn);

		leftPanel.add(buttonsPanel);

		ScaledImagePanel imagePanel =
				new ScaledImagePanel("assets/ChessIcon.png");

		imagePanel.setBorder(
				new EmptyBorder(80, 80, 0, 80)
				);

		JPanel contentPanel =
				new JPanel(new GridLayout(1, 2));

		contentPanel.setBackground(
				GuiStyle.backgroundColor()
				);

		contentPanel.add(leftPanel);
		contentPanel.add(imagePanel);

		rootPanel.add(
				contentPanel,
				BorderLayout.CENTER
				);

		setContentPane(rootPanel);

		setVisible(true);
	}
}