package gamePackage;

import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Gameplay {
    
    private Scanner scanner;
    private Random rand;
    private boolean continueGame = true;
    private int damage;
    private int health = 10;
    private int agility;
    
    // constructor
    public Gameplay() {
        this.scanner = new Scanner(System.in);
        this.rand = new Random();
    }
    
    public void startGame() {
        
        
        System.out.println("Welcome to the world of Lancelot! \n");
        System.out.println("You are an aspiring knight wishing to become King Arthurs sworn 'Holy Knight'!\n"
        + "To achieve this you must defeat the current holy Knight.\n");
        // give a break so the user isnt spammed with text - ended up only using this once and added 
        // the "-------" to break up chunks 
        try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                
            }
        System.out.println("But first... what is your name? [enter 'x' to exit]: \n");
        String username = scanner.nextLine();
        
        try {
            FileWriter writer = new FileWriter("username.txt");
            writer.write(username);
            writer.close();
        }
        catch (IOException e) {
        }
        
            // switch statement for entering username 
         switch(username){
             case "x":
                 System.out.println("Exiting the game.");
                 continueGame = false;
                 return;
             default:
                 
                 System.out.print("Wow ");
                 
                 try {
                     FileReader reader = new FileReader("username.txt");
                     BufferedReader inputStream = new BufferedReader(reader);
                     String line = null;
                     
                     while((line = inputStream.readLine())!=null)
                         System.out.print(line);
                     inputStream.close();
                 }
                 catch(IOException e) {
                     System.out.print("Error reading from file");
                 }
                 System.out.println("! What a powerful name! Welcome to Lancelot!");
                 System.out.println("------------------------------------------------------------------------------------");
                 break;
         }
    }
    
    public boolean attributes() {
        // if the user selects 'x' during select damage we dont want select agility to run
        if(!selectDamage()) {
            return false;
        }
        selectAgility();
        return continueGame;
    }
        
    public boolean selectDamage() {
        // output to console
        System.out.println("\nIt is time for you to be granted your powers. \nCome! "
         + "Let me place the Ring of Power over you to discover your stats!\n");
        
        
        System.out.println("As your race is Human you have " + health + " Health Points!\n"
        + "Additionally, the Ring of Power will allow you to gain between 1-10 damage points and agility points");
        
        System.out.println("Press 'd' to reveal how many damage points you have!");
        
        // user input 
        String input = scanner.nextLine();
            
        // switch statements allowing user to exit game or see their damage using random 
            switch (input.toLowerCase()) {
                case "x":
                    System.out.println("Exiting the game.");
                    continueGame = false;
                    break;
                case "d":
                    damage = rand.nextInt(10) + 1;
                    System.out.println("Your Damage points are " + damage + "!");
                    if(damage <=3) {
                        System.out.println("Unlucky.. you're quite weak it seems. \n"
                                + "Oh well! at least you can get stronger by fighting monsters!");
                    }
                    else if (damage > 3 && damage <=6) {
                        System.out.println("Hm acceptable... you'll manage.. but it wont be easy.");
                    }
                    else {
                        System.out.println("Wow! A rare talent! let's hope your agility is as high as your strength!");
                    }
                    
                    break;
                default:
                    System.out.println("great.... now we have to start all over again.");
                    selectDamage();
                    break;
            }
           return continueGame; 
    }
    
    public boolean selectAgility() {
           
            // text for user to read
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("\nTime to find out how nimble you are!\nRemember the higher agility one has, "
                    + "the more likely you are to avoid incoming attacks!");
            
            System.out.println("press 'a' to reveal how many agility points you have!");
            
            // user input again (exactly the same as selectDamage())
            String input = scanner.nextLine();
            
            // same as selectDamage() shows the user their agility 
            switch(input.toLowerCase()) {
                case "x":
                    System.out.println("Exiting the game.");
                    continueGame = false;
                    break;
                case "a":    
                    agility = rand.nextInt(10) + 1;
                    System.out.println("Your Agility points are " + agility + "!");
                    if(agility <=3) {
                        System.out.println("Wow how can someone be so slow... \n"
                                + "Oh well! at least you can get quicker by slaying monsters!");
                    }
                    else if (agility > 3 && agility <=6) {
                        System.out.println("Hm acceptable... you'll manage.. but it wont be easy.");
                    }
                    else {
                        System.out.println("Wow! A rare talent! with such agility you'll be able to dodge anything!!");
                    }
                    break;
                default:
                    // use recursion until user enters 'x' or 'a'
                    System.out.println("great... now we have to start all over again...");
                    selectAgility();
                    break;
            
            }
            return continueGame;
    }
    
    
    public int getAgility() {
        return this.agility;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public void setContinueGame(boolean value) {
        continueGame = value;
    }
    
    public boolean isGameContinuing() {
        return this.continueGame;
    }
    
}
