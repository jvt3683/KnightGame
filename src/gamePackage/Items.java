
package gamePackage;

import java.util.ArrayList;

public class Items {
    private ArrayList<String> weapons;
    private int killCount;
   
    
    public Items() {
       weapons = new ArrayList<>();
       weapons.add("Fists");
       weapons.add("Dagger");
       weapons.add("Sword");
       weapons.add("Great Sword");
       weapons.add("Holy Sword");
       
       killCount = 0;
    }
    
    public void currentWeapon() {
        System.out.print(weapons.get(killCount));
    }
    
    public int getKillCount() {
        return killCount;
    }
    public void increaseKillCount() {
        killCount++;
    }
}
