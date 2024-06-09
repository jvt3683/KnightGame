package KnightGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HolyKnightPanel extends JPanel {
    private GameMain gameMain;
    private HolyKnight holyKnight;
    private JTextArea battleLogArea;
    private JButton attackButton;
    private JButton nextButton;

    public HolyKnightPanel(GameMain gameMain, Characters characters) {
        this.gameMain = gameMain;
        this.holyKnight = new HolyKnight(characters);

        //layout -> battle log -> buttons -> actions
        setLayout(new BorderLayout());

        battleLogArea = new JTextArea();
        battleLogArea.setEditable(false);
        battleLogArea.setLineWrap(true);
        battleLogArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(battleLogArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        attackButton = new JButton("Attack");
        nextButton = new JButton("Next Round");
        buttonPanel.add(attackButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

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

    //start the fight
    public void startBattle() {
        holyKnight.startBattle();
        updateBattleLog();
        attackButton.setVisible(false);
        nextButton.setVisible(true);
    }
    //next round unless knight is dead
    public void nextRound() {
        holyKnight.nextRound();
        updateBattleLog();

        if (!holyKnight.isCharacterAlive() || !holyKnight.isHolyKnightAlive()) {
            handleBattleEnd();
        }
    }
    //death message
    private void handleBattleEnd() {
        if (!holyKnight.isCharacterAlive()) {
            JOptionPane.showMessageDialog(this, "You have been defeated by the Holy Knight...");
            //close game
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "After a long journey of struggle and death, you stand before the "
                    + "Holy Knight's body. \nHis blood slowly runs down the temple stairs as he says his last words.\n"
                    + "You are now the Holy Knight... It is your duty to protect the world from all danger.\n"
                    + "Congratulations.");
            //go to congrats message 
            gameMain.showCongratulationsPanel(); 
        }
    }

    private void updateBattleLog() {
        battleLogArea.setText(holyKnight.getBattleLog());
    }
}