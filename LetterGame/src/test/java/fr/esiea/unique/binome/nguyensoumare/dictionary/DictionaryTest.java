package fr.esiea.unique.binome.nguyensoumare.dictionary;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {

    private Dictionary dictionary;

    @Before
    public void setup() {
        dictionary = new Dictionary();

    }

    @Test
    public void isAwordTest() {
        String pkeyword = "successeur";
        assertTrue(dictionary.isAword(pkeyword));

    }

}
