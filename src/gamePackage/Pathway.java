package gamePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Pathway {
    
    private Gameplay gameplay;
    private Scanner scanner;
    private int battleChosen = -1;

    //takes in Gameplay object so Pathway can use its methods
   public Pathway(Gameplay gameplay) {
       this.gameplay = gameplay;
       this.scanner = new Scanner(System.in);
   }
    
    public void printChoice() {
        
        int damage = gameplay.getDamage();
        int agility = gameplay.getAgility();
        // fileIO reads the text file 'choices' and prints it out until the next line is null
        try (BufferedReader reader = new BufferedReader(new FileReader("choices.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(IOException e) {
                    System.err.println("Error reading choices");
                    }
        // my favourite part of the game (method will be called repeatedly and provides a grinding aspect
        System.out.println("What would you like to choose? [1/2/3/4]");
        System.out.println("If this is your first time playing choose '4'");
        int option;
        // if user doesnt enter a number it uses recursion to call again for proper input
        try {
            option = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("\nYou're a knight.. you should know the difference between numbers and letter by now..\n");
            printChoice();
            return;
        }
        
        switch(option) {
            case 1:
                // battleChosen will determine whether battleOne/Two/Three occurs
                battleChosen = 1;
                System.out.println("A wise chioce, good luck battling your foe");
                break;
            case 2:
                battleChosen = 2;
                System.out.println("Best of luck in the Arena, my leige.");
                break;
            case 3:
                battleChosen = 3;
                System.out.println("Best of luck! I wish you greatest success");
                break;
            
            case 4:
                System.out.println("\nRecommendation: \nFight the beasts until your damage and agility stats are at least 9");
                System.out.println("Fight in the Arena if your stats combined are over 17");
                System.out.println("Challenge the Holy Knight if your combined stats are above 26\n");
                System.out.println("IMPORTANT!! Try to fight the monsters multiple times to level up your stats the fastest.");
                printChoice();
                break;
                // if they enter the wrong number the game sets 'setContinueGame' to false making the game quit
            default:
                System.out.println("not choosing any huh.. I knew you were weak... better luck next life.");
                gameplay.setContinueGame(false);
                break;
        }
    }
    public int getBattleChosen() {
        return this.battleChosen;
    }
}

