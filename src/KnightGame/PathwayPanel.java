package KnightGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PathwayPanel extends JPanel {
    private GameMain gameMain;
    private Gameplay gameplay;
    private Pathway pathway;
    private JTextArea outputArea;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;
    private JButton option4Button;
    private JButton restartButton;

    public PathwayPanel(GameMain gameMain, Gameplay gameplay, Pathway pathway) {
        this.gameMain = gameMain;
        this.gameplay = gameplay;
        this.pathway = pathway;

        
        setLayout(new BorderLayout());

        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        //used gridlayout bc idk css and i had 5 buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        option1Button = new JButton("Fight a Monster");
        option2Button = new JButton("Enter the Arena");
        option3Button = new JButton("Challenge the Holy Knight");
        option4Button = new JButton("Get Advice");
        restartButton = new JButton("Restart Game");

        ///adding buttons + events
        buttonPanel.add(option1Button);
        buttonPanel.add(option2Button);
        buttonPanel.add(option3Button);
        buttonPanel.add(option4Button);
        buttonPanel.add(restartButton);

        add(buttonPanel, BorderLayout.SOUTH);

        option1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectOption(1);
            }
        });

        option2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectOption(2);
            }
        });

        option3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectOption(3);
            }
        });

        option4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayText(getAdvice());
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }
    //text asking user to pick
    public void updateChoices() {
        displayText("\n" + pathway.getChoicesText());
        displayText("What would you like to choose? [1/2/3/4/5]");
        displayText("If this is your first time playing, choose '4' for advice. Choose '5' to restart the game.\n");
    }

    private void selectOption(int option) {
        String result = pathway.handleChoice(option);
        displayText(result);

        //transition to whatever panel u choose
        if (option == 1 && pathway.getBattleChosen() == 1) {
            gameMain.showMonsterBattlePanel();
        } else if (option == 2 && pathway.getBattleChosen() == 2) {
            gameMain.showArenaPanel();
        } else if (option == 3 && pathway.getBattleChosen() == 3) {
            gameMain.showHolyKnightPanel();
        } else if (pathway.getBattleChosen() > 0) {
        }
    }
    //one of the requirements is restart!
    private void restartGame() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to restart the game?", "Restart Game", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            gameMain.restartGame();
        }
    }

    private void displayText(String text) {
        outputArea.append(text + "\n");
    }
    //button option 4
    private String getAdvice() {
        Characters characters = gameMain.getCharacters();
        int damage = characters.getDamage();
        int agility = characters.getAgility();
        int combinedStats = damage + agility;

        return String.format("\nRecommendation:\n"
                + "Fight the beasts until your damage and agility stats are at least 9.\n"
                + "Fight in the Arena if your stats combined are over 17 (Current Combined Stats: %d).\n"
                + "Challenge the Holy Knight if your combined stats are above 26 (Current Combined Stats: %d).\n"
                + "IMPORTANT!! Try to fight the monsters multiple times to level up your stats the fastest.\n",
                combinedStats, combinedStats);
    }
}