package KnightGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GamePanel extends JPanel {
    private GameMain gameMain;
    private Gameplay gameplay;
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton enterButton;
    private JButton damageButton;
    private JButton agilityButton;
    private JButton nextButton;
    private String username;

    public GamePanel(GameMain gameMain, Gameplay gameplay) {
        this.gameMain = gameMain;
        this.gameplay = gameplay;

        // layout
        setLayout(new BorderLayout());

        //output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        //fields and buttons for input
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        enterButton = new JButton("Enter");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(enterButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        //dmg agility and next button
        JPanel buttonPanel = new JPanel();
        damageButton = new JButton("Reveal Damage");
        agilityButton = new JButton("Reveal Agility");
        nextButton = new JButton("Next");
        buttonPanel.add(damageButton);
        buttonPanel.add(agilityButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.NORTH);

        //hide til we need
        damageButton.setVisible(false);
        agilityButton.setVisible(false);
        nextButton.setVisible(false);

        //enter button actionlistener
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleInput(inputField.getText());
                inputField.setText("");
            }
        });

        //same for damage/agility buttons
        damageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revealDamage();
            }
        });

        agilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revealAgility();
            }
        });

        //bug where it would skip to next scene so you couldnt see ur agility stat so added another button
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                proceedToPathway();
            }
        });

        //start game
        startGame();
    }

    private void startGame() {
        displayText("Welcome to the world of Lancelot!\n");
        displayText("You are an aspiring knight wishing to become King Arthur's sworn 'Holy Knight'!\n"
                + "To achieve this, you must defeat the current Holy Knight.\n");
        displayText("But first... what is your name? \n");
    }

    private void handleInput(String input) {
        input = input.trim();
        if (username == null) {
            if (input.isEmpty()) {
                displayText("Please enter a valid name.\n");
            } else {
                username = input;
                saveUsername(username);
                displayText("Wow " + username + "! What a powerful name! Welcome to Lancelot!\n"
                        + "------------------------------------------------------------------------------------");
                displayText("\nIt is time for you to be granted your powers.\nCome! "
                        + "Let me place the Ring of Power over you to discover your stats!\n"
                        + "Click 'Reveal Damage' to see how many damage points you have!");

                //show button
                damageButton.setVisible(true);
                //cannot enter 
                inputField.setEnabled(false);
                enterButton.setEnabled(false);
            }
        }
    }

    private void revealDamage() {
        if (gameplay.selectDamage()) {
            displayText(gameplay.getDamageDescription());
            //update daamge
            gameMain.getCharacters().setDamage(gameplay.getDamage());
            damageButton.setVisible(false);
            agilityButton.setVisible(true);
            displayText("Click 'Reveal Agility' to see how many agility points you have!");
        }
    }

    private void revealAgility() {
        if (gameplay.selectAgility()) {
            displayText(gameplay.getAgilityDescription());
            // Update characters agility
            gameMain.getCharacters().setAgility(gameplay.getAgility());
            agilityButton.setVisible(false);
            displayText("Game setup complete. You are ready to start your adventure!\n");

            //show next button (from the bug message above)
            nextButton.setVisible(true);
        }
    }

    private void proceedToPathway() {
        //start pathway
        gameMain.showPathwayPanel();
    }

    private void displayText(String text) {
        outputArea.append(text + "\n");
    }

    private void saveUsername(String username) {
        try (FileWriter writer = new FileWriter("username.txt")) {
            writer.write(username);
        } catch (IOException e) {
            displayText("Error saving username.");
        }
    }
}