package fr.esiea.unique.binome.nguyensoumare.playerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import fr.esiea.unique.binome.nguyensoumare.letterGameEngine.LetterGenerator;
import fr.esiea.unique.binome.nguyensoumare.letterGameEngine.SharedBag;

public class Player {

    private String name;
    public ArrayList<Character> currentLetters;
    public HashMap<String, String> wordsfound;
    public boolean bWordFound;

    public Player(String name) {
        currentLetters = new ArrayList<Character>();
        wordsfound = new HashMap<String, String>();
        this.name = name;
        this.bWordFound = false;
    }

    public String toString() {
        return name;
    }

    public boolean getbWordFound() {
        return (this.bWordFound);
    }

    public void setbWordFound(boolean b) {
        this.bWordFound = b;
    }

    public HashMap<String, String> getWordsFound() {
        return wordsfound;

    }

    public void addword(String pkeyWord, String pword) {
        if (pword.contains("-")) {
            String[] splitString = pword.split("-");
            for (String splitWord : splitString) {
                String key = splitWord;
                key = key.replaceAll("�|�", "a");
                key = key.replaceAll("�|�", "o");
                key = key.replaceAll("��", "u");
                key = key.replaceAll("�", "i");
                key = key.replaceAll("�|�", "e");
                wordsfound.put(key, splitWord);
            }
        } else {
            wordsfound.put(pkeyWord, pword);
        }

    }

    public char pick(LetterGenerator g) {
        return (g.pickLetter());
    }

    public void removeWordFound(String word) {
        wordsfound.remove(word);

    }

    public String makeword(SharedBag p_sharedbag, ArrayList<String> p_lListPlayer) {

        String entry = "";
        System.out.println("enter the word : ");
        Scanner scan = new Scanner(System.in);
        entry = scan.next();
        return entry;

    }

    public boolean isWordIn(String word) {
        boolean b = false;
        if (wordsfound.get(word) != null) {
            b = true;
        }
        return b;
    }

    public String getWordused() {
        return "";

    }
}
