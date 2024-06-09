/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KnightGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArenaPanel extends JPanel {
    private final GameMain gameMain;
    private final Arena arena;
    private final JTextArea battleLogArea;
    private final JButton attackButton;
    private final JButton nextButton;

    public ArenaPanel(GameMain gameMain, Characters characters) {
        this.gameMain = gameMain;
        this.arena = new Arena(characters);

        // setup layout
        setLayout(new BorderLayout());

        // battle log area
        battleLogArea = new JTextArea();
        battleLogArea.setEditable(false);
        battleLogArea.setLineWrap(true);
        battleLogArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(battleLogArea);
        add(scrollPane, BorderLayout.CENTER);

        //uttons
        JPanel buttonPanel = new JPanel();
        attackButton = new JButton("Attack");
        nextButton = new JButton("Next Round");
        buttonPanel.add(attackButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // action listeners
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startBattle();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextRound();
            }
        });
        
        nextButton.setVisible(false);
    }
    //start the battle
    public void startBattle() {
        arena.startBattle();
        updateBattleLog();
        attackButton.setVisible(false);
        nextButton.setVisible(true);
    }
    //if someone dies call handleBattleEnd
    public void nextRound() {
        arena.nextRound();
        updateBattleLog();

        if (!arena.isCharacterAlive() || !arena.isOpponentAlive()) {
            handleBattleEnd();
        }
    }
    //if we die end hte game, if we win then go choose what to do next
    private void handleBattleEnd() {
        if (!arena.isCharacterAlive()) {
            JOptionPane.showMessageDialog(this, "You have been defeated in the Arena...");
            System.exit(0); // Close the application
        } else {
            JOptionPane.showMessageDialog(this, "You have won the Arena battle!");
            gameMain.showPathwayPanel();
        }
    }

    private void updateBattleLog() {
        battleLogArea.setText(arena.getBattleLog());
    }
}