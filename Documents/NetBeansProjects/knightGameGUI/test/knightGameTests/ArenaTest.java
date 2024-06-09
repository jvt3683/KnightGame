
package knightGameTests;

import KnightGame.Characters;
import KnightGame.Arena;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArenaTest {
    @Test
    public void testArenaVictory() {
        Characters player = new Characters(18, 7, 8);
        Arena arena = new Arena(player);
        
        arena.startBattle();
        
        //battle til someone dies
        while (arena.isOpponentAlive() && player.getHealth() > 0) {
            arena.nextRound();
        }
        
        assertFalse(arena.isOpponentAlive());
        assertTrue(player.getHealth() > 0);
        assertEquals(10, player.getAgility());
        assertEquals(9, player.getDamage());
    }
}
