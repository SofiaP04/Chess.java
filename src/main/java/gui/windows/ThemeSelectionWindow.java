package gui.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import gui.components.CustomButton;
import gui.listeners.ApplyThemeListener;
import gui.theme.ThemeLoader;
import gui.theme.ThemeManager;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ThemeSelectionWindow extends JFrame {

	public ThemeSelectionWindow() {

		setTitle("Select Theme");
		setSize(450, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel root = new JPanel(new BorderLayout());
		root.setBackground(Color.WHITE);
		root.setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel title = new JLabel(
				"Choose a Theme",
				SwingConstants.CENTER
				);

		title.setFont(new Font(
				"Serif",
				Font.BOLD,
				40
				));

		title.setBorder(new EmptyBorder(0, 0, 30, 0));

		root.add(title, BorderLayout.NORTH);

		JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		List<String> themes = loadThemeNames();

		for (String theme : themes) {

			CustomButton button = new CustomButton(theme);

			button.setAlignmentX(Component.CENTER_ALIGNMENT);

			button.addActionListener(
					new ApplyThemeListener(
							this,
							theme
							)
					);

			listPanel.add(button);
			listPanel.add(Box.createVerticalStrut(15));
		}

		JScrollPane scroll = new JScrollPane(listPanel);
		scroll.setBorder(null);

		root.add(scroll, BorderLayout.CENTER);

		setContentPane(root);
	}

	private List<String> loadThemeNames() {

		List<String> names = new ArrayList<>();

		try {

			File file = new File("assets/themes/themes.txt");

			List<String> lines =
					FileUtils.readLines(
							file,
							StandardCharsets.UTF_8
							);

			for (String line : lines) {

				line = line.trim();

				if (line.startsWith("[")
						&& line.endsWith("]")) {

					names.add(
							line.substring(
									1,
									line.length() - 1
									)
							);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return names;
	}
	
}