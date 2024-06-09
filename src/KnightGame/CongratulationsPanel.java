package KnightGame;

import javax.swing.*;
import java.awt.*;

public class CongratulationsPanel extends JPanel {
    public CongratulationsPanel() {
        //layout
        setLayout(new BorderLayout());

        //endgame msg
        JTextArea messageArea = new JTextArea();
        messageArea.setText("Congratulations! You have become the new Holy Knight!\n"
                + "Thank you for playing.");
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Arial", Font.BOLD, 16));
        messageArea.setBackground(getBackground());
        messageArea.setOpaque(false);

        // msg added to panel
        add(messageArea, BorderLayout.CENTER);

        //end game
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> System.exit(0));

        //button added at bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}