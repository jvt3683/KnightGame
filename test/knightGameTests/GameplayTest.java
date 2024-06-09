/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package knightGameTests;

import KnightGame.Gameplay;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameplayTest {
    private Gameplay gameplay;

    @Before
    public void setUp() {
        gameplay = new Gameplay();
    }

    @Test
    public void testInitialization() {
        assertTrue("Game should continue by default.", gameplay.isGameContinuing());
        assertEquals("Initial damage should be 0.", 0, gameplay.getDamage());
        assertEquals("Initial agility should be 0.", 0, gameplay.getAgility());
    }
}