package knightGameTests;

import KnightGame.MonsterBattle;
import KnightGame.Characters;
import org.junit.Test;
import static org.junit.Assert.*;

public class MonsterBattleTest {
    @Test
    public void testMonsterBattleVictory() {
        //new player
        Characters player = new Characters(15, 5, 4);
        MonsterBattle monsterBattle = new MonsterBattle(player);
        
        monsterBattle.startBattle();
        
        //fight til monster defeated, then check stats
        while (monsterBattle.isMonsterAlive() && player.getHealth() > 0) {
            monsterBattle.nextRound();
        }
        
        assertFalse(monsterBattle.isMonsterAlive());
        assertTrue(player.getHealth() > 0);
        assertEquals(6, player.getAgility());
        assertEquals(7, player.getDamage());
    }
}