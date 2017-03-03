package fr.esiea.unique.binome.nguyensoumare.playerType;

import java.util.ArrayList;
import java.util.Collections;

import fr.esiea.unique.binome.nguyensoumare.dictionary.Dictionary;
import fr.esiea.unique.binome.nguyensoumare.letterGameEngine.SharedBag;

public class PlayerAI extends Player {

    public Dictionary dictionary;
    private ArrayList<String> listWord;
    private String wordUsed;

    public PlayerAI(Dictionary dictionary, String name) {
        super(name);
        this.dictionary = dictionary;
        listWord = new ArrayList<String>();
    }

    public String findword(SharedBag p_sharedBag, ArrayList<String> p_lListWordsPlayers, boolean p_bWordFromSharedBagOnly) {
        int indiceWordUse = -1;
        // la liste est tri� de mani�re descendante
        Collections.sort(listWord, new Sorter());
        if (!p_bWordFromSharedBagOnly) {
            indiceWordUse = p_sharedBag.verifyAllLettersOfWordIn(p_lListWordsPlayers, listWord.get(0));
            if (indiceWordUse != -1) {
                wordUsed = p_lListWordsPlayers.get(indiceWordUse);
                return p_lListWordsPlayers.get(indiceWordUse);
            }
        }

        return listWord.get(0);

    }

    @Override

    public String makeword(SharedBag p_shareBag, ArrayList<String> p_lListPlayer) {
        wordUsed = "";
        String result = "";
        listWord.clear();
        ArrayList<Character> allLetters = new ArrayList<Character>();

        allLetters.addAll(p_shareBag.getlListOfLetterOnSharedBag());
        listWordsGenerator(allLetters, "", p_shareBag);

        if (!listWord.isEmpty()) {
            result = findword(p_shareBag, p_lListPlayer, true);
        } else {
            if (!p_lListPlayer.isEmpty()) {
                for (String word : p_lListPlayer) {
                    allLetters.clear();
                    Character[] charObjectArray = word.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
                    Collections.addAll(allLetters, charObjectArray);
                    allLetters.addAll(p_shareBag.getlListOfLetterOnSharedBag());
                    listWordsGenerator(allLetters, word, p_shareBag);
                }
                if (!listWord.isEmpty()) {
                    result = findword(p_shareBag, p_lListPlayer, false);
                }
            }
        }

        return result;

    }

    public void listWordsGenerator(ArrayList<Character> lListOfLetterOnSharedBag, String wordUsed, SharedBag sharedBag) {
        createwords(lListOfLetterOnSharedBag, "", sharedBag, wordUsed);

    }

    public void createwords(ArrayList<Character> p_lListLetters, String p_sWordFormed, SharedBag p_sharedBag, String p_sWordUsed) {

        String sWordFormed = p_sWordFormed;

        if (p_lListLetters.isEmpty() == false) {

            for (int k = 0; k < p_lListLetters.size(); k++) {

                ArrayList<Character> lListLettersCopy = new ArrayList<Character>();
                lListLettersCopy.addAll(p_lListLetters);
                sWordFormed = p_sWordFormed + p_lListLetters.get(k);
                ArrayList<String> listDictionary = new ArrayList<String>();
                listDictionary.addAll(dictionary.getDictionary().keySet());

                for (int i = 0; i < listDictionary.size(); i++) {
                    if (listDictionary.get(i).startsWith(sWordFormed)) {

                        if (!listWord.contains(sWordFormed)) {
                            if (dictionary.isAword(sWordFormed)) {
                                ArrayList<String> l = new ArrayList<String>();
                                l.add(p_sWordUsed);
                                if (p_sharedBag.verifyletters(null, sWordFormed)) {
                                    listWord.add(sWordFormed);
                                } else {
                                    if (p_sharedBag.verifyAllLettersOfWordIn(l, sWordFormed) != -1) {
                                        if (!isWordIn(sWordFormed) && sWordFormed.length() > p_sWordUsed.length()) {
                                            listWord.add(sWordFormed);
                                        }
                                    }
                                }
                            }

                        }

                        if (p_lListLetters.isEmpty() == false) {
                            lListLettersCopy.remove(k);
                            createwords(lListLettersCopy, sWordFormed, p_sharedBag, p_sWordUsed);
                            i = dictionary.getDictionary().size();
                        }

                    }

                }

            }
        }

    }

    @Override
    public String getWordused() {
        return wordUsed;
    }

    public void setWordused(String p_wordUsed) {
        this.wordUsed = p_wordUsed;
    }

    public ArrayList<String> getListWord() {
        return listWord;
    }

    public void setListWord(ArrayList<String> listWord) {
        this.listWord = listWord;
    }

    public void addListWord(String p_sWord) {
        listWord.add(p_sWord);
    }
}
