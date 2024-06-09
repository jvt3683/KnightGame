package knightGameTests;

import KnightGame.HolyKnight;
import KnightGame.Characters;
import org.junit.Test;
import static org.junit.Assert.*;

public class HolyKnightTest {
    @Test
    public void testIsCharacterAlive() {
        //create random character
        Characters player = new Characters(15, 5, 4); 
        HolyKnight holyKnight = new HolyKnight(player);
        
        //fight but keep player alive and check if they are alive
        holyKnight.startBattle();
        holyKnight.nextRound();
        boolean result = holyKnight.isCharacterAlive();
        assertTrue("Player should still be alive.", result);
    }

    @Test
    public void testHolyKnightVictory() {
        //new character with high stats to kill the knight
        Characters player = new Characters(25, 10, 12);
        HolyKnight holyKnight = new HolyKnight(player);

       //fight til knight dead
        holyKnight.startBattle();
        while (holyKnight.isHolyKnightAlive() && player.getHealth() > 0) {
            holyKnight.nextRound();
        }
        
        //test outcomes
        assertFalse("The Holy Knight should be defeated.", holyKnight.isHolyKnightAlive());
        assertTrue("Player should still be alive.", player.getHealth() > 0);
        assertTrue("Holy Knight should be marked as dead.", holyKnight.isHolyKnightDead());
    }
}