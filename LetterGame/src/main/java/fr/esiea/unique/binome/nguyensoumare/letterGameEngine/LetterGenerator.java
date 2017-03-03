package fr.esiea.unique.binome.nguyensoumare.letterGameEngine;

import java.security.SecureRandom;

public class LetterGenerator {
    private SecureRandom rand;

    public LetterGenerator() {
        rand = new SecureRandom();
    }

    public char pickLetter() {

        char letterPicked;
        letterPicked = (char) (rand.nextInt(26) + 'a');
        return letterPicked;

    }

}
