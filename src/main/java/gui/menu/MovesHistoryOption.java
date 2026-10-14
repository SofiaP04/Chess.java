package gui.menu;

import core.facade.GameFacade;

import javax.swing.*;
import java.awt.*;

public final class MovesHistoryOption {

    private MovesHistoryOption() {
    }

    public static void show(
            JFrame parent,
            GameFacade game
    ) {

        JTextArea area = new JTextArea(
                MoveHistoryFormatter.format(
                        game.getMoveHistory()
                )
        );

        area.setEditable(false);

        area.setFont(
                new Font(
                        Font.MONOSPACED,
                        Font.PLAIN,
                        14
                )
        );

        area.setLineWrap(false);
        area.setCaretPosition(0);

        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setPreferredSize(
                new Dimension(450, 300)
        );

        JOptionPane.showMessageDialog(
                parent,
                scrollPane,
                "Move History",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}