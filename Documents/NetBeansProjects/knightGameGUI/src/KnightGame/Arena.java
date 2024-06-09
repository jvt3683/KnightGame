/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KnightGame;

public class Arena extends Characters {
    private final Characters characters;
    private int originalHealth;
    private final StringBuilder battleLog;

    //creating enemy
    public Arena(Characters characters) {
        super(15, 5, 6);
        this.characters = characters;
        this.battleLog = new StringBuilder();
    }
    //setting and resetting enemy health each round
    public void startBattle() {
        this.setHealth(15);
        this.originalHealth = characters.getHealth();
        battleLog.setLength(0); 
        
        //text on screen
        battleLog.append("-------------------------------------------------\n");
        battleLog.append("You walk into the Arena, weapon in hand.\n");
        battleLog.append("The stench of blood assaults your nostrils, and you see your opponent.\n");
        battleLog.append("Reflecting upon yourself, you see your stats:\n\n");
        battleLog.append("Health: ").append(characters.getHealth()).append("\n");
        battleLog.append("Damage: ").append(characters.getDamage()).append("\n");
        battleLog.append("Agility: ").append(characters.getAgility()).append("\n");
        battleLog.append("--------------------------------------------------\n");
        battleLog.append("The opponent standing before you has stats of:\n");
        battleLog.append("Health: ").append(getHealth()).append("\n");
        battleLog.append("Damage: ").append(getDamage()).append("\n");
        battleLog.append("Agility: ").append(getAgility()).append("\n");
    }

    // keep fighting as long as user/enemy health > 0
    public boolean isCharacterAlive() {
        return characters.getHealth() > 0;
    }
    public boolean isOpponentAlive() {
        return this.getHealth() > 0;
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    public String nextRound() {
        battleLog.append("-----------------------------------------------\n");
        battleLog.append("The battle continues!\n");

        //Our turn to attack
        if (!this.dodge()) {
            battleLog.append("You attack the opponent, dealing ").append(characters.getDamage()).append(" damage.\n");
            setHealth(this.getHealth() - characters.getDamage());
        } else {
            battleLog.append("The opponent dodged your attack!\n");
        }
        //shows enemy health
        battleLog.append("Opponent's current health: ").append(getHealth()).append("\n");
        
        //ends the battle if health <= 0
        if (getHealth() <= 0) {
            return endBattle(true);
        }

        // enemy turn (same as our turn)
        if (!characters.dodge()) {
            battleLog.append("The opponent attacks you, dealing ").append(getDamage()).append(" damage.\n");
            characters.setHealth(characters.getHealth() - getDamage());
        } else {
            battleLog.append("You dodged the opponent's attack!\n");
        }
        battleLog.append("Your current health: ").append(characters.getHealth()).append("\n");

        if (characters.getHealth() <= 0) {
            return endBattle(false);
        }

        return battleLog.toString();
    }
    //if we win reset our health and add stats 
    private String endBattle(boolean characterWon) {
        if (characterWon) {
            battleLog.append("The opponent is defeated!\n");
            characters.setHealth(originalHealth);
            characters.setHealth(characters.getHealth() + 2);
            characters.setDamage(characters.getDamage() + 2);
            characters.setAgility(characters.getAgility() + 2);
            
            battleLog.append("Your health is restored to ").append(originalHealth).append(" and stats increased:\n");
            battleLog.append("New stats - Health: ").append(characters.getHealth())
                    .append(", Damage: ").append(characters.getDamage())
                    .append(", Agility: ").append(characters.getAgility()).append("\n");
        } else {
            battleLog.append("You have been defeated in the Arena...\n");
        }
        return battleLog.toString();
    }
}