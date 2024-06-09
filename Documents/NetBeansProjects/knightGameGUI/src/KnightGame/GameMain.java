package KnightGame;

import javax.swing.*;
import java.awt.*;

public class GameMain {
    private JFrame frame;
    private GamePanel gamePanel;
    private PathwayPanel pathwayPanel;
    private MonsterBattlePanel monsterBattlePanel;
    private ArenaPanel arenaPanel;
    private HolyKnightPanel holyKnightPanel;
    private CongratulationsPanel congratulationsPanel;
    private Gameplay gameplay;
    private Characters characters;

    public GameMain() {
        // init frame/size/pos
        frame = new JFrame("Lancelot Game");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new CardLayout());

        // init gamelogic
        gameplay = new Gameplay();
        characters = new Characters(10, gameplay.getDamage(), gameplay.getAgility());

        //init panels
        gamePanel = new GamePanel(this, gameplay);
        pathwayPanel = new PathwayPanel(this, gameplay, new Pathway(gameplay));

        //adding panel to frame
        frame.add(gamePanel, "GamePanel");
        frame.add(pathwayPanel, "PathwayPanel");
        showGamePanel();
        frame.setVisible(true);
    }

    public Characters getCharacters() {
        return characters;
    }

    public void showGamePanel() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "GamePanel");
    }

    public void showPathwayPanel() {
        pathwayPanel.updateChoices();
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "PathwayPanel");
    }

    public void showMonsterBattlePanel() {
        monsterBattlePanel = new MonsterBattlePanel(this, characters);
        frame.add(monsterBattlePanel, "MonsterBattlePanel");
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MonsterBattlePanel");
    }

    public void showArenaPanel() {
        arenaPanel = new ArenaPanel(this, characters);
        frame.add(arenaPanel, "ArenaPanel");
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "ArenaPanel");
    }

    public void showHolyKnightPanel() {
        holyKnightPanel = new HolyKnightPanel(this, characters);
        frame.add(holyKnightPanel, "HolyKnightPanel");
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "HolyKnightPanel");
    }

    public void showCongratulationsPanel() {
        congratulationsPanel = new CongratulationsPanel();
        frame.add(congratulationsPanel, "CongratulationsPanel");
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "CongratulationsPanel");
    }

    
    public void restartGame() {
        // reset game
        gameplay = new Gameplay();
        characters = new Characters(10, gameplay.getDamage(), gameplay.getAgility());

        //reitinialize panels
        gamePanel = new GamePanel(this, gameplay);
        pathwayPanel = new PathwayPanel(this, gameplay, new Pathway(gameplay));
        frame.add(gamePanel, "GamePanel");
        frame.add(pathwayPanel, "PathwayPanel");

        //go to gamepanel
        showGamePanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameMain());
    }
}