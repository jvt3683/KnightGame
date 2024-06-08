package gamePackage;

import java.util.Scanner;
public class HolyKnight extends Characters {
    
    private Gameplay gameplay;
    private UserInfo userInfo;
    private Items items;
    private boolean holyKnightKilled;
    private Scanner scanner;
    
    public HolyKnight(Gameplay gameplay, UserInfo userInfo, Items items) {
        super(23, 7, 11);
        this.gameplay = gameplay;
        this.userInfo = userInfo;
        this.items = items;
        this.holyKnightKilled = false;
        this.scanner = new Scanner(System.in);
        
    }
    public void battle(){
        
        int originalHealth = userInfo.getHealth();
        this.setHealth(20);
        System.out.println("You slowly walk into the Holy Knight's temple");
        System.out.println("You remember every battle you have partaken in... everything has lead you to this moment");
        System.out.println("\nThe Holy Knight stands before you, he gazes into the sunset behind you");
        System.out.println("You are not the first, and you will not be the last");
        System.out.println("I can tell you have put in the work, but it is not enough to defeat me.");
        System.out.println("---------------------------------------------");
        System.out.println("\"Enough talking\" you say... it's time to fight.");
        
        
        System.out.println("Reflecting upon yourself, you see your stats: \n\n" + "Health: " + userInfo.getHealth() 
                + "\nDamage: " + userInfo.getDamage() + 
                "\nAgility: " + userInfo.getAgility() );
        
        System.out.println("--------------------------------------------------");
        System.out.println("The Holy Knight standing before you has stats of: \n");
        System.out.println("Health: "+ getHealth());
        System.out.println("Damage: "+ getDamage());
        System.out.println("Agility: "+ getAgility());
        
        int round = 1;
        // Fighting in the arena 
        while (userInfo.getHealth() > 0 && this.getHealth() > 0) {
            System.out.println("-----------------------------------------------");
            System.out.println("Round " + round + " begins!");
            System.out.println("It's too late to back out now! Press any key to attack!");
            String letter = scanner.nextLine();
            
            if(!this.dodge()) {
                
                System.out.print("You raise your ");
                items.currentWeapon();
                System.out.println(" and charge at the Knight before you, striking him with incredible force");
                setHealth(this.getHealth() - userInfo.getDamage());
                System.out.println("The enemies current health is: " + getHealth());  
            } 
            else {
                System.out.print("You raise your ");
                items.currentWeapon();
                System.out.println(" and charge at the enemy standing in front of you, charging up before.... OH NO, HE DODGED");
                System.out.println("The enemies current health is: " + getHealth());
            }
            
            if(getHealth() <= 0){
                break;
            }
            // enemies turn to attack
            System.out.println("Quick! try to dodge! press any key");
            letter = scanner.nextLine();
            
            if (!userInfo.dodge()) {
                System.out.println("The Holy Knight rushes towards you with incredible speed");
                userInfo.setHealth(userInfo.getHealth() - getDamage());
                System.out.println("You got hit! Your remaining health is: " + userInfo.getHealth());
            }
            else {
                System.out.println("Nice Dodge! keep fighting hard the next exchange is about to begin!");
                System.out.println("Your health is: " + userInfo.getHealth());
                System.out.println("-----------------------------------");
            }
            round++;
        }
        
        //end the game if you die
        if(userInfo.getHealth() <= 0) {
            System.out.println("Game Over..... maybe in another life!");
            gameplay.setContinueGame(false);
        }
        else {
            System.out.println("The Holy Knight lies before you, covered in blood....");
            System.out.println("\"I was wrong about you...\" he says with his last breath");
            System.out.println("\"You must carry on the Holy Knight legacy...\"");
            System.out.println("------------------------------------------");
            setKnightLife(true);
            
            if(items.getKillCount() < 4) {
                items.increaseKillCount();
                System.out.println("Your Kill Count is now: " + items.getKillCount());
                System.out.println("-----------------------------------------------");
                System.out.println("What's this?? The enemy dropped something....  you reach down to pick it up...");
                System.out.print("Weapon Unlocked: ");
                items.currentWeapon();
                
            }
        }
    }
    public boolean isHolyKnightDead() {
        return this.holyKnightKilled;
    }
    
    public void setKnightLife(boolean holyKnightKilled) {
        this.holyKnightKilled = holyKnightKilled;
    }
}




