package KnightGame;

public class HolyKnight extends Characters {
    private final Characters characters;
    private boolean holyKnightKilled;
    private int originalHealth;
    private StringBuilder battleLog;

        //set enemy stats
    public HolyKnight(Characters characters) {
        super(23, 7, 11);
        this.characters = characters;
        this.holyKnightKilled = false;
        this.battleLog = new StringBuilder();
    }

    public void startBattle() {
        this.setHealth(20);
        this.originalHealth = characters.getHealth();
        battleLog.setLength(0); 
        
        //text
        battleLog.append("-------------------------------------------------\n");
        battleLog.append("You slowly walk into the Holy Knight's temple.\n");
        battleLog.append("You remember every battle you have partaken in... everything has led you to this moment.\n");
        battleLog.append("\nThe Holy Knight stands before you, gazing into the sunset behind you.\n");
        battleLog.append("You are not the first, and you will not be the last.\n");
        battleLog.append("I can tell you have put in the work, but it is not enough to defeat me.\n");
        battleLog.append("---------------------------------------------\n");
        battleLog.append("\"Enough talking,\" you say... it's time to fight.\n");

        battleLog.append("Reflecting upon yourself, you see your stats:\n\n");
        battleLog.append("Health: ").append(characters.getHealth()).append("\n");
        battleLog.append("Damage: ").append(characters.getDamage()).append("\n");
        battleLog.append("Agility: ").append(characters.getAgility()).append("\n");
        battleLog.append("--------------------------------------------------\n");
        battleLog.append("The Holy Knight standing before you has stats of:\n");
        battleLog.append("Health: ").append(getHealth()).append("\n");
        battleLog.append("Damage: ").append(getDamage()).append("\n");
        battleLog.append("Agility: ").append(getAgility()).append("\n");
    }

    public boolean isCharacterAlive() {
        return characters.getHealth() > 0;
    }

    public boolean isHolyKnightAlive() {
        return this.getHealth() > 0;
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    public String nextRound() {
        battleLog.append("-----------------------------------------------\n");
        battleLog.append("The battle continues!\n");

        // our turn
        if (!this.dodge()) {
            battleLog.append("You attack the Holy Knight, dealing ").append(characters.getDamage()).append(" damage.\n");
            setHealth(this.getHealth() - characters.getDamage());
        } else {
            battleLog.append("The Holy Knight dodged your attack!\n");
        }
        battleLog.append("Holy Knight's current health: ").append(getHealth()).append("\n");

        if (getHealth() <= 0) {
            return endBattle(true);
        }

        //enemy turn
        if (!characters.dodge()) {
            battleLog.append("The Holy Knight attacks you, dealing ").append(getDamage()).append(" damage.\n");
            characters.setHealth(characters.getHealth() - getDamage());
        } else {
            battleLog.append("You dodged the Holy Knight's attack!\n");
        }
        battleLog.append("Your current health: ").append(characters.getHealth()).append("\n");

        if (characters.getHealth() <= 0) {
            return endBattle(false);
        }

        return battleLog.toString();
    }
    //win/lose messages
    private String endBattle(boolean characterWon) {
        if (characterWon) {
            battleLog.append("The Holy Knight lies before you, covered in blood....\n");
            battleLog.append("\"I was wrong about you,\" he says with his last breath.\n");
            battleLog.append("\"You must carry on the Holy Knight legacy...\"\n");
            battleLog.append("------------------------------------------\n");
            setKnightLife(true);

            characters.setHealth(originalHealth);
            characters.setHealth(characters.getHealth() + 1);
            characters.setDamage(characters.getDamage() + 1);
            characters.setAgility(characters.getAgility() + 2);

            battleLog.append("Your health is restored to ").append(originalHealth).append(" and stats increased:\n");
            battleLog.append("New stats - Health: ").append(characters.getHealth())
                    .append(", Damage: ").append(characters.getDamage())
                    .append(", Agility: ").append(characters.getAgility()).append("\n");
        } else {
            battleLog.append("You have been defeated by the Holy Knight...\n");
        }
        return battleLog.toString();
    }

    public boolean isHolyKnightDead() {
        return this.holyKnightKilled;
    }

    public void setKnightLife(boolean holyKnightKilled) {
        this.holyKnightKilled = holyKnightKilled;
    }
}