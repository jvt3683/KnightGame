package gamePackage;

public class Main {
    
    public static void main(String[] args) {
        //instantiating gameplay object
        
        Gameplay gameplay = new Gameplay();
        
        // starts the game with an intro and username
        gameplay.startGame();
        
        Pathway pathway = new Pathway(gameplay);
      
        if (gameplay.isGameContinuing() && gameplay.attributes()) {
        }
        
        //instantiating all of the objects needed for the 'gameplay' 
        Items items = new Items();
        UserInfo userInfo = new UserInfo(gameplay, gameplay.getDamage(), gameplay.getAgility());
        MonsterBattle monsterBattle = new MonsterBattle(gameplay, userInfo, items);
        HolyKnight holyKnight = new HolyKnight(gameplay, userInfo, items);
        Arena arena = new Arena(gameplay, userInfo, items);
        
        
        while(gameplay.isGameContinuing() && !holyKnight.isHolyKnightDead()) {
            if(gameplay.isGameContinuing()) {
                pathway.printChoice();
            }
            if(gameplay.isGameContinuing()) {
            switch (pathway.getBattleChosen()) {
                case 1:
                    monsterBattle.battle();
                    break;
                case 2:
                    arena.battle();
                    break;
                default:
                    holyKnight.battle();
                    break;}
        }
        }
        
        if(holyKnight.isHolyKnightDead()) {
        System.out.println("Well... your journey has come to an end it seems");
        System.out.println("You are now the Lancelot's Holy Knight");
        System.out.println("Continue to grow and protect this land.");
        System.out.println("It's fate lays in your hand");
        System.out.println("Fin.");
        }
    }
}


    

