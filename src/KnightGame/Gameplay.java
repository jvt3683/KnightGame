/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KnightGame;


import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Gameplay {
    private final Random rand;
    private boolean continueGame = true;
    private int damage;
    private final int health = 10;
    private int agility;
    private String username;
    
    public Gameplay() {
        this.rand = new Random();
    }

    //start game
    public void setUsername(String username) {
        this.username = username;
        try {
            FileWriter writer = new FileWriter("username.txt");
            writer.write(username);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean selectDamage() {
        if (!continueGame) {
            return false;
        }
        damage = rand.nextInt(10) + 1;
        return true;
    }
    //!continueGame isnt a thing in the GUI version just ignore it 
    public boolean selectAgility() {
        if (!continueGame) {
            return false;
        }
        agility = rand.nextInt(10) + 1;
        return true;
    }
    //if else for damage/agility stats
    public String getDamageDescription() {
        if (damage <= 3) {
            return "\nYour Damage points are " + damage + "! Unlucky.. you're quite weak it seems.\n"
                    + "Oh well! At least you can get stronger by fighting monsters!";
        } else if (damage > 3 && damage <= 6) {
            return "\nYour Damage points are " + damage + "! Hm acceptable... you'll manage.. but it won't be easy.";
        } else {
            return "\nYour Damage points are " + damage + "! Wow! A rare talent! Let's hope your agility is as high as your strength!";
        }
    }
    public String getAgilityDescription() {
        if (agility <= 3) {
            return "\nYour Agility points are " + agility + "! Wow how can someone be so slow...\n"
                    + "Oh well! At least you can get quicker by slaying monsters!";
        } else if (agility > 3 && agility <= 6) {
            return "\nYour Agility points are " + agility + "! Hm acceptable... you'll manage.. but it won't be easy.";
        } else {
            return "\nYour Agility points are " + agility + "! Wow! A rare talent! With such agility you'll be able to dodge anything!!";
        }
    }

    //getters/setters!!
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