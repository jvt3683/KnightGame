
package KnightGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pathway {
    private Gameplay gameplay;
    private int battleChosen = -1;

    public Pathway(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public String getChoicesText() {
        StringBuilder choicesText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("choices.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                choicesText.append(line).append("\n");
            }
        } catch (IOException e) {
            choicesText.append("");
        }
        return choicesText.toString();
    }
    //text for whatever option u pick
    public String handleChoice(int option) {
        switch (option) {
            case 1:
                battleChosen = 1;
                return "A wise choice, good luck battling your foe!";
            case 2:
                battleChosen = 2;
                return "Best of luck in the Arena, my liege.";
            case 3:
                battleChosen = 3;
                return "Best of luck! I wish you the greatest success.";
            case 4:
                return getAdvice();
            default:
                gameplay.setContinueGame(false);
                return "Not choosing any huh? I knew you were weak... better luck next life.";
        }
    }
    //button 4 option text
    private String getAdvice() {
        int damage = gameplay.getDamage();
        int agility = gameplay.getAgility();
        return String.format("\nRecommendation:\n"
                + "Fight the beasts until your damage and agility stats are at least 9.\n"
                + "Fight in the Arena if your stats combined are over 17 (Current Combined Stats: %d).\n"
                + "Challenge the Holy Knight if your combined stats are above 26 (Current Combined Stats: %d).\n"
                + "IMPORTANT!! Try to fight the monsters multiple times to level up your stats the fastest.\n",
                damage, agility, damage + agility, damage + agility);
    }

    public int getBattleChosen() {
        return this.battleChosen;
    }
}