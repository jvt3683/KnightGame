/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package knightGameTests;
import KnightGame.Characters;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharactersTest {
    private Characters character;

    @Before
    public void setUp() {
        //init character with random values
        character = new Characters(10, 5, 3);
    }

    @Test
    public void testInitialization() {
        assertEquals("Health should be initialized to 10.", 10, character.getHealth());
        assertEquals("Damage should be initialized to 5.", 5, character.getDamage());
        assertEquals("Agility should be initialized to 3.", 3, character.getAgility());
    }

    @Test
    public void testSettersAndGetters() {
        character.setHealth(20);
        assertEquals("Health should be set to 20.", 20, character.getHealth());

        character.setDamage(8);
        assertEquals("Damage should be set to 8.", 8, character.getDamage());

        character.setAgility(5);
        assertEquals("Agility should be set to 5.", 5, character.getAgility());
    }
}