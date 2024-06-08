package gamePackage;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class MonsterBattle extends Characters {
    
    private final Gameplay gameplay;
    private final UserInfo userInfo;
    private final Scanner scanner;
    private final Items items;
    private final Random rand;
    private final HashMap<Integer, String> dialogue;
    
    
    public MonsterBattle(Gameplay gameplay, UserInfo userInfo, Items items) {
        super(8,3,2);
        this.gameplay = gameplay;
        this.userInfo = userInfo;
        this.items = items;
        this.scanner = new Scanner(System.in);
        this.rand = new Random();
        
        dialogue = new HashMap<>();
        
        dialogue.put(1, "YOU SEEK DEATH, HUMAN");
        dialogue.put(2, "YOU DARE STAND BEFORE ME??!");
        dialogue.put(3, "KRAAAWHHHHHHHH");
        dialogue.put(4, "SCROOOOWLLLLLL");
        dialogue.put(5, "HOOOOOWLLLLLLLL");
    }
    
    public void battle() {
        // sets the monsters health back to 8 each time it fights
       this.setHealth(8);
       int randKey = rand.nextInt(5) + 1;  
       int dodge = rand.nextInt(10) + 1;
       int itemDrop = rand.nextInt(5) + 1;
       int round = 1;
       int originalHealth = userInfo.getHealth();
               
        System.out.println("-------------------------------------------------");
        System.out.println("As you walk down the stone path deeper into the "
                + "woods, you notice the sunlight begin to vanish");
        System.out.println("Out of no where a monster appears before you \"" + dialogue.get(randKey) + "\"");
        System.out.println("Your blood runs cold as you look at the fierce creature before you.. you know it is time to fight...");
        System.out.println("Reflecting upon yourself, you see your stats: \n\n" + "Health: " + userInfo.getHealth() 
                + "\nDamage: " + userInfo.getDamage() + 
                "\nAgility: " + userInfo.getAgility() );
        
        System.out.println("--------------------------------------------------");
        System.out.println("The monster standing before you has stats of: \n");
        System.out.println("Health: "+ getHealth());
        System.out.println("Damage: "+ getDamage());
        System.out.println("Agility: "+ getAgility());
        
        
        // keep running until someone dies 
        while (userInfo.getHealth() > 0 && this.getHealth() > 0) {
            System.out.println("-----------------------------------------------");
            System.out.println("Round " + round + " begins!");
            System.out.println("It's too late to back out now! Press any key to attack!");
            String letter = scanner.nextLine();
            
            if(!this.dodge()) {
                
                System.out.print("You raise your ");
                items.currentWeapon();
                System.out.println(" and charge at the beast before you, hitting them with incredible force");
                setHealth(this.getHealth() - userInfo.getDamage());
                System.out.println("The enemies current health is: " + getHealth());  
            } 
            else {
                System.out.print("You raise your ");
                items.currentWeapon();
                System.out.println(" and charge at the beast before you, charging up before.... DAMN IT, THEY DODGED");
                System.out.println("The enemies current health is: " + getHealth());
            }
            
            if(getHealth() <= 0){
                break;
            }
            // monsters turn to attack
            System.out.println("Quick! try to dodge! press any key");
            letter = scanner.nextLine();
            
            if (!userInfo.dodge()) {
                System.out.println("The beast rushes towards you violently, attacking you");
                userInfo.setHealth(userInfo.getHealth() - getDamage());
                System.out.println("You got hit! Your remaining health is: " + userInfo.getHealth());
            }
            else {
                System.out.println("Nice Dodge! keep fighting hard the next exchange is about to begin!");
                System.out.println("Your health is: " + userInfo.getHealth());
                System.out.println("---------------------------------------");
            }
            round++;
        }
        
        //end the game if you die
        if(userInfo.getHealth() <= 0) {
            System.out.println("Game Over..... better luck next time!");
            gameplay.setContinueGame(false);
        }
        else {
            System.out.println("Well done on slaying your enemy.");
            
            if(items.getKillCount() < 4) {
                items.increaseKillCount();
                System.out.println("Your Kill Count is now: " + items.getKillCount());
                System.out.println("-----------------------------------------------");
                System.out.println("What's this?? The monster dropped something....  you reach down to pick it up...");
                System.out.print("Weapon Unlocked: ");
                items.currentWeapon();
            }
             System.out.println("\n-----------------------------------------------");
            System.out.println("! \nCongratulations!, you also found a potion, you drink it, regaining your health!");
            System.out.println("Level Up! All stats + 2");
            System.out.println("(Maximum Damage and Agility: 10)");
            
            userInfo.setHealth(originalHealth + 2);
            
            if(userInfo.getAgility() < 9) {
                userInfo.setAgility(userInfo.getAgility() + 2);
            }
            if (userInfo.getDamage() < 9) {
            userInfo.setDamage(userInfo.getDamage() + 2);
            }
            
            System.out.println(userInfo.getHealth());
            System.out.println(userInfo.getDamage());
            System.out.println(userInfo.getAgility());
            
            
            
            System.out.println("--------------------------------------------");
            System.out.println("Adventurer, what would you like to do next?");
            
        }
    }
}
