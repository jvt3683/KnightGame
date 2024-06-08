package gamePackage;

public class Characters {
    
    private int health;
    private int damage;
    private int agility;
    
    public Characters(int health, int damage, int agility) {
        this.health = health;
        this.damage = damage;
        this.agility = agility;
    }
    
    //method for dodging in battles
    public boolean dodge() {
        // multiply user agility by 0.05, if the number is greater than the random num gen then they dodge! yippee
        double dodge = this.getAgility() * 0.05;
        return Math.random() < dodge;
    }
    
    // getters n setters
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
