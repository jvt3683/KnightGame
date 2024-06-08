package gamePackage;

public class UserInfo extends Characters {
    
    private Gameplay gameplay;
    
    public UserInfo(Gameplay gameplay, int damage, int agility) {
        super(10, damage , agility);
        this.gameplay = gameplay;
    }
    
    public void userStats() {
        System.out.println(getHealth());
        System.out.println(getDamage());
        System.out.println(getAgility());
        
    }   
}

