package fr.esiea.unique.binome.nguyensoumare.letterGameEngine;

import java.util.ArrayList;
import java.util.Collections;

public class SharedBag {

    private ArrayList<Character> lListOfLetterOnSharedBag;

    public SharedBag() {

        this.lListOfLetterOnSharedBag = new ArrayList<Character>();

    }

    public boolean addLetter(char p_cLetter) {
        if (p_cLetter == '\u0000') {
            return false;
        } else {
            lListOfLetterOnSharedBag.add(p_cLetter);
            return true;
        }

    }

    public boolean clearBag() {
        lListOfLetterOnSharedBag.clear();
        if (lListOfLetterOnSharedBag.isEmpty()) {
            return true;
        }
        return false;
    }

    public void removeLetters(String wordToRemove) {

        for (char c : wordToRemove.toCharArray()) {
            Character charRemove = new Character(c);
            lListOfLetterOnSharedBag.remove(charRemove);
        }
    }

    public ArrayList<Character> getlListOfLetterOnSharedBag() {
        return lListOfLetterOnSharedBag;

    }

    public boolean verifyletters(ArrayList<String> p_lListwords, String p_sWord) {
        ArrayList<Character> listletters = new ArrayList<Character>();
        listletters.addAll(lListOfLetterOnSharedBag);
        if (p_lListwords != null) {
            for (String s : p_lListwords) {
                Character[] charObjectArray = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
                Collections.addAll(listletters, charObjectArray);
            }

        }

        boolean check = true;
        for (char c : p_sWord.toCharArray()) {
            if (listletters.contains(c) == false) {
                check = false;
            } else {
                Character charO = new Character(c);
                listletters.remove(charO);
            }
        }
        return check;
    }

    public int verifyAllLettersOfWordIn(ArrayList<String> p_lListword, String p_sWord) {
        // lettersToDelete = lettersToDelete.replaceFirst(s, "");

        int indice = -1;
        for (int k = 0; k < p_lListword.size(); k++) {
            String wordInList = p_lListword.get(k);
            String wordcopie = p_sWord;
            char[] temp = wordInList.toCharArray();
            for (int i = 0; i < temp.length; i++) {

                String letter = "" + temp[i];
                if (p_sWord.contains(letter) == true) {
                    wordcopie = wordcopie.replaceFirst(letter, "");
                    wordInList = wordInList.replaceFirst(letter, "");
                }
            }

            if (wordInList.length() == 0) {
                if (wordcopie != "") {
                    if (verifyletters(null, wordcopie)) {
                        indice = k;
                        k = p_lListword.size();
                    }
                }

            }
        }
        return indice;

    }

}
