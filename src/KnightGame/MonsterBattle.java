package KnightGame;

import java.util.HashMap;
import java.util.Random;

public class MonsterBattle extends Characters {
    private final Characters characters;
    private final Random rand;
    private final HashMap<Integer, String> dialogue;
    private int round;
    private StringBuilder battleLog;
    private int originalHealth;

    //setting monster stats
    public MonsterBattle(Characters characters) {
        super(7, 2, 2);
        this.characters = characters;
        this.rand = new Random();
        this.dialogue = new HashMap<>();
        this.round = 1;
        this.battleLog = new StringBuilder();

        //hashmap for dialogue - not as impressive to me after going through dsa... :(
        dialogue.put(1, "YOU SEEK DEATH, HUMAN");
        dialogue.put(2, "YOU DARE STAND BEFORE ME??!");
        dialogue.put(3, "KRAAAWHHHHHHHH");
        dialogue.put(4, "SCROOOOWLLLLLL");
        dialogue.put(5, "HOOOOOWLLLLLLLL");
    }

    public void startBattle() {
        //reset health for multiple rounds
        //store our health for the end
        this.setHealth(7);
        this.round = 1;
        //clear battlelog
        this.battleLog.setLength(0); 
        this.originalHealth = characters.getHealth(); 

        //picks random voiceline
        int randKey = rand.nextInt(5) + 1;
        battleLog.append("-------------------------------------------------\n");
        battleLog.append("As you walk down the stone path deeper into the woods, you notice the sunlight begin to vanish.\n");
        battleLog.append("Out of nowhere a monster appears before you: \"" + dialogue.get(randKey) + "\"\n");
        battleLog.append("Your blood runs cold as you look at the fierce creature before you. You know it is time to fight...\n");
        battleLog.append("--------------------------------------------------\n");
        battleLog.append("Your stats:\n");
        battleLog.append("Health: ").append(characters.getHealth()).append("\n");
        battleLog.append("Damage: ").append(characters.getDamage()).append("\n");
        battleLog.append("Agility: ").append(characters.getAgility()).append("\n");
        battleLog.append("--------------------------------------------------\n");
        battleLog.append("Monster stats:\n");
        battleLog.append("Health: ").append(getHealth()).append("\n");
        battleLog.append("Damage: ").append(getDamage()).append("\n");
        battleLog.append("Agility: ").append(getAgility()).append("\n");
    }

    public boolean isCharacterAlive() {
        return characters.getHealth() > 0;
    }

    public boolean isMonsterAlive() {
        return this.getHealth() > 0;
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    public String nextRound() {
        battleLog.append("-----------------------------------------------\n");
        battleLog.append("Round ").append(round).append(" begins!\n");

        //our turn (if they dont dodge we damage them, same for their turn)
        if (!this.dodge()) {
            battleLog.append("You attack the monster, dealing ").append(characters.getDamage()).append(" damage.\n");
            setHealth(this.getHealth() - characters.getDamage());
        } else {
            battleLog.append("The monster dodged your attack!\n");
        }
        battleLog.append("Monster's current health: ").append(getHealth()).append("\n\n");

        if (getHealth() <= 0) {
            return endBattle(true);
        }

        // enemy's turn
        if (!characters.dodge()) {
            battleLog.append("The monster attacks you, dealing ").append(getDamage()).append(" damage.\n");
            characters.setHealth(characters.getHealth() - getDamage());
        } else {
            battleLog.append("You dodged the monster's attack!\n");
        }
        battleLog.append("Your current health: ").append(characters.getHealth()).append("\n");

        if (characters.getHealth() <= 0) {
            return endBattle(false);
        }

        round++;
        return battleLog.toString();
    }

    private String endBattle(boolean characterWon) {
        if (characterWon) {
            battleLog.append("The monster is defeated!\n");
            //reset hp then add stats
            characters.setHealth(originalHealth);
            characters.setHealth(characters.getHealth() + 2);
            characters.setDamage(characters.getDamage() + 2);
            characters.setAgility(characters.getAgility() + 2);

            battleLog.append("Your health is restored to ").append(originalHealth).append(" and all stats increased by 2.\n");
            battleLog.append("New stats - Health: ").append(characters.getHealth())
                    .append(", Damage: ").append(characters.getDamage())
                    .append(", Agility: ").append(characters.getAgility()).append("\n");
        } else {
            battleLog.append("You have been defeated by the monster...\n");
        }
        return battleLog.toString();
    }
}