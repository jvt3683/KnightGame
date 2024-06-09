package KnightGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MonsterBattlePanel extends JPanel {
    private GameMain gameMain;
    private MonsterBattle monsterBattle;
    private JTextArea battleLogArea;
    private JButton attackButton;
    private JButton nextButton;

    public MonsterBattlePanel(GameMain gameMain, Characters characters) {
        this.gameMain = gameMain;
        this.monsterBattle = new MonsterBattle(characters);

        //layout
        setLayout(new BorderLayout());

        //b log area
        battleLogArea = new JTextArea();
        battleLogArea.setEditable(false);
        battleLogArea.setLineWrap(true);
        battleLogArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(battleLogArea);
        add(scrollPane, BorderLayout.CENTER);

        //buttons
        JPanel buttonPanel = new JPanel();
        attackButton = new JButton("Attack");
        nextButton = new JButton("Next Round");
        buttonPanel.add(attackButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //action listeners
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

    public void startBattle() {
        monsterBattle.startBattle();
        updateBattleLog();
        attackButton.setVisible(false);
        nextButton.setVisible(true);
    }
    //start next round and update text, if someone dies call battleEnd method
    public void nextRound() {
        monsterBattle.nextRound();
        updateBattleLog();

        if (!monsterBattle.isCharacterAlive() || !monsterBattle.isMonsterAlive()) {
            handleBattleEnd();
        }
    }

    //if someone dies game exits or u continue to pathwaypanel again
    private void handleBattleEnd() {
        if (!monsterBattle.isCharacterAlive()) {
            JOptionPane.showMessageDialog(this, "You died... better luck in your next life... try again.");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You have defeated the monster!");
            gameMain.showPathwayPanel();
        }
    }

    private void updateBattleLog() {
        battleLogArea.setText(monsterBattle.getBattleLog());
    }
}