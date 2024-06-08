package gamePackage;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Arena extends Characters {
    
    private Gameplay gameplay;
    private UserInfo userInfo;
    private Items items;
    private Scanner scanner;
    
    // constructor
    public Arena(Gameplay gameplay, UserInfo userInfo, Items items) {
        super(14,5,6);
        this.gameplay = gameplay;
        this.userInfo = userInfo;
        this.items = items;
        this.scanner = new Scanner(System.in);
    }
    // the battle method for fighting in the Arena
    public void battle(){
        
        //store og health so we can restore after the battle 
        int originalHealth = userInfo.getHealth();
        // this sets the enemies health to 15 
        this.setHealth(15);
        
        System.out.println("You walk into the Arena, weapon in hand.");
        System.out.println("The stench of blood assaults your nostrils, and you see");
        System.out.println("Please write a short note for your family in case you do not return:");
        String lastMessage = scanner.nextLine();
        try {
            FileWriter writer = new FileWriter("lastMessage.txt");
            writer.write(lastMessage);
            writer.close();
        }
        catch(IOException e) {
        }
        System.out.println("\nThank you. Let me repeat the message to make sure it is correct.");
        try {
            FileReader reader = new FileReader("lastMessage.txt");
            BufferedReader inputStream = new BufferedReader(reader);
            String line = null;
            
            while((line = inputStream.readLine())!= null) {
                System.out.println(line);
                inputStream.close();
            }
        }
        catch(IOException e) {
        }
        
        System.out.println("Reflecting upon yourself, you see your stats: \n\n" + "Health: " + userInfo.getHealth() 
                + "\nDamage: " + userInfo.getDamage() + 
                "\nAgility: " + userInfo.getAgility() );
        
        System.out.println("--------------------------------------------------");
        System.out.println("The man in the Arena standing before you has stats of: \n");
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
                System.out.println(" and charge at the man before you, striking him with incredible force");
                setHealth(this.getHealth() - userInfo.getDamage());
                System.out.println("The enemies current health is: " + getHealth());  
            } 
            else {
                System.out.print("You raise your ");
                items.currentWeapon();
                System.out.println(" and charge at the enemy standing in front of you, charging up before.... DAMN IT, THEY DODGED");
                System.out.println("The enemies current health is: " + getHealth());
            }
            
            if(getHealth() <= 0){
                break;
            }
            // enemies turn to attack
            System.out.println("Quick! try to dodge! press any key");
            letter = scanner.nextLine();
            
            if (!userInfo.dodge()) {
                System.out.println("The man rushes towards you violently attacking you");
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
                System.out.println("What's this?? The enemy dropped something....  you reach down to pick it up...");
                System.out.print("Weapon Unlocked: ");
                items.currentWeapon();
            }
                System.out.println("\n-----------------------------------------------");
            
            
            System.out.println("! \nCongratulations!, you also found a potion, you drink it, regaining your health!");
            System.out.println("Level Up! Health + 1, Damage + 1, Agility + 2");
            System.out.println("(Maximum Damage and Agility: 20)");
            
            userInfo.setHealth(originalHealth + 1);
            
            if(userInfo.getAgility() < 20) {
                userInfo.setAgility(userInfo.getAgility() + 2);
            }
            if (userInfo.getDamage() <= 18) {
            userInfo.setDamage(userInfo.getDamage() + 2);
            }
            
            System.out.println(userInfo.getHealth());
            System.out.println(userInfo.getDamage());
            System.out.println(userInfo.getAgility());
            
            System.out.println("--------------------------------------------");
            System.out.println("Adventurer, what would you like to do next?");
            System.out.println("If you want to exit press 'x', otherwise press any key");
            String exit  = scanner.nextLine();
            
            if (exit.equalsIgnoreCase("x")) {
                gameplay.setContinueGame(false);
                System.out.println("Exiting...");  
            } 
            else {
                System.out.println("Let's continue!");
            }
        }
}
}
