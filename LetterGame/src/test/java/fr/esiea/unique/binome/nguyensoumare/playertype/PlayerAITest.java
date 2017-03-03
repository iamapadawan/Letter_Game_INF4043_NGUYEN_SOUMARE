package fr.esiea.unique.binome.nguyensoumare.playertype;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.unique.binome.nguyensoumare.dictionary.Dictionary;
import fr.esiea.unique.binome.nguyensoumare.letterGameEngine.SharedBag;
import fr.esiea.unique.binome.nguyensoumare.playerType.PlayerAI;

public class PlayerAITest {

    private SharedBag sharedBag;
    private PlayerAI playerAI;
    private Dictionary dictionary;

    @Before
    public void setup() {
        sharedBag = new SharedBag();
        sharedBag.addLetter('s');
        dictionary = new Dictionary();
        playerAI = new PlayerAI(dictionary, "AI");
        playerAI.addListWord("nage");
        playerAI.addListWord("abreges");
        playerAI.addListWord("vole");
    }

    @Test
    public void findwordTest() {
        ArrayList<String> listwordplayersfound = new ArrayList<String>();
        listwordplayersfound.add("nuage");
        listwordplayersfound.add("neige");
        listwordplayersfound.add("abrege");
        String wordused = playerAI.findword(sharedBag, listwordplayersfound, false);
        assertEquals(wordused, "abrege");
    }
}
