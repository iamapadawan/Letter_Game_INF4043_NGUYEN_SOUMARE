package fr.esiea.unique.binome.nguyensoumare.playertype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.unique.binome.nguyensoumare.playerType.Player;

public class PlayerTest {

    private Player playerHuman;

    @Before
    public void setup() {

        playerHuman = new Player("PlayerNampe");

    }

    @Test
    public void AddWordTest() {
        String pkeyword = "acquitte";
        String pword = "acquitt�";
        playerHuman.addword(pkeyword, pword);
        assertEquals(playerHuman.getWordsFound().get(pkeyword), pword);
        assertTrue(playerHuman.getWordsFound().containsKey(pkeyword));

    }

    @Test
    public void removeWordFoundTest() {
        String pkeyword = "acquitte";
        playerHuman.removeWordFound(pkeyword);
        assertFalse(playerHuman.getWordsFound().containsKey(pkeyword));
    }

    @Test
    public void isWordInTest() {
        String pkeyword = "acquitte";
        String pword = "acquitt�";
        playerHuman.addword(pkeyword, pword);
        assertTrue(playerHuman.isWordIn(pkeyword));
    }

}
