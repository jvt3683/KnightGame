package KnightGame;

public class Characters {
    private int health;
    private int damage;
    private int agility;
    //creating character stats
    public Characters(int health, int damage, int agility) {
        this.health = health;
        this.damage = damage;
        this.agility = agility;
    }

    // method for dodging
    public boolean dodge() {
        double dodgeChance = this.agility * 0.05;
        return Math.random() < dodgeChance;
    }

    // Getters and setters
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return this.damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAgility() {
        return this.agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
}