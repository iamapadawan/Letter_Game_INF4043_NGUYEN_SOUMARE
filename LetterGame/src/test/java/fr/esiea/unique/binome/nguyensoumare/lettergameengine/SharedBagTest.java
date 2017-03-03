package fr.esiea.unique.binome.nguyensoumare.lettergameengine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.unique.binome.nguyensoumare.letterGameEngine.SharedBag;

public class SharedBagTest {

    private SharedBag sharedBag;
    private ArrayList<String> listwords;

    @Before
    public void setup() {
        sharedBag = new SharedBag();
        sharedBag.addLetter('a');
        sharedBag.addLetter('b');
        sharedBag.addLetter('n');
        sharedBag.addLetter('c');
        sharedBag.addLetter('s');

        listwords = new ArrayList<String>();
    }

    @Test
    public void addLetterTest() {
        char c = 'd';
        sharedBag.addLetter(c);
        assertTrue(sharedBag.getlListOfLetterOnSharedBag().contains(c));
    }

    // verify if all letters of the word is in the sharedbag
    @Test
    public void verifylettersTest1() {
        String word = "cas";
        sharedBag.verifyletters(null, word);
    }

    // verify if letters of the word is made of the sharedbag and/or the word letters of players.
    @Test
    public void verifylettersTest2() {
        ArrayList<String> listwords = new ArrayList<String>();
        listwords.add("age");
        String word = "nage";
        assertTrue(sharedBag.verifyletters(listwords, word));
    }

    // verify if letters of the word is made of a complete word (all letters of the words)
    @Test
    public void verifyAllLettersOfWordInTest() {
        listwords.add("age");
        String word = "nage";
        assertNotEquals(sharedBag.verifyAllLettersOfWordIn(listwords, word), -1);
    }

    @Test
    public void removeLettersTest() {
        assertFalse(sharedBag.getlListOfLetterOnSharedBag().contains("b"));
    }

    @Test
    public void clearBagTest() {
        sharedBag.clearBag();
        assertTrue(sharedBag.getlListOfLetterOnSharedBag().isEmpty());
    }

}
