package core.ai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AiEngineConnector {

	private Process process;

	private BufferedReader reader;
	private BufferedWriter writer;

	public void connect() throws IOException {

		process = new ProcessBuilder(
				"assets/engine/stockfish-windows-x86-64-universal.exe"
				).start();

		reader = new BufferedReader(
				new InputStreamReader(
						process.getInputStream()
						)
				);

		writer = new BufferedWriter(
				new OutputStreamWriter(
						process.getOutputStream()
						)
				);
	}

	public void sendCommand(String command)
			throws IOException {

		writer.write(command);
		writer.newLine();
		writer.flush();
	}

	public String readLine()
			throws IOException {

		return reader.readLine();
	}

	public String readUntil(String token)
			throws IOException {

		StringBuilder output = new StringBuilder();

		String line;

		while ((line = reader.readLine()) != null) {

			output.append(line)
			.append(System.lineSeparator());

			if (line.startsWith(token)) {
				break;
			}
		}

		return output.toString();
	}

	public boolean isConnected() {
		return process != null && process.isAlive();
	}

	public void disconnect() {

		try {

			if (writer != null) {
				writer.close();
			}

			if (reader != null) {
				reader.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (process != null) {
			process.destroy();
		}
	}
}