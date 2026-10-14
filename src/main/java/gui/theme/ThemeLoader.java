package gui.theme;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ThemeLoader {

	public static Theme load(String themeName) {

		Theme theme = new Theme();

		try {

			File file = new File("assets/themes/themes.txt");

			List<String> lines =
					FileUtils.readLines(
							file,
							StandardCharsets.UTF_8
							);

			boolean inSection = false;

			for (String line : lines) {

				line = line.trim();

				if (line.isEmpty() || line.startsWith("#"))
					continue;

				if (line.equalsIgnoreCase("[" + themeName + "]")) {
					inSection = true;
					continue;
				}

				if (line.startsWith("[") && inSection) {
					break;
				}

				if (inSection && line.contains("=")) {

					String[] parts = line.split("=");

					if (parts.length == 2) {
						theme.set(parts[0].trim(), parts[1].trim());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return theme;
	}
}