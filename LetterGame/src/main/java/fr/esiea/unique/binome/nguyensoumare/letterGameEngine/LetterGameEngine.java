package fr.esiea.unique.binome.nguyensoumare.letterGameEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import fr.esiea.unique.binome.nguyensoumare.dictionary.Dictionary;
import fr.esiea.unique.binome.nguyensoumare.playerType.Player;
import fr.esiea.unique.binome.nguyensoumare.playerType.PlayerAI;

public class LetterGameEngine {

    private Dictionary dictionary;
    private LetterGenerator randomLetter;
    private SharedBag sharedBag;
    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private Scanner scan;

    public LetterGameEngine() {

        String entry = "";

        while (!entry.equals("q")) {

            System.out.println("Hello, What do you want to do (h for help)\n");
            scan = new Scanner(System.in);
            entry = scan.next();

            switch (entry) {

            case "h": // help : afficher l'aide

                System.out.println("h: Print this help ");
                System.out.println("n: start a new game");
                System.out.println("q: Quit");
                break;

            case "n": // start a new game
                startGame();
                play();
                break;

            case "q":
                break;
            }
        }

    }

    public static void main(String[] args) {
        LetterGameEngine LetterGameConsole = new LetterGameEngine();
    }

    public void startGame() {
        initializeGame();
        String entry = "";
        System.out.println("Let's play!!! ");
        String choice = "";
        boolean b = true;
        while (b) {
            System.out.println("Do you want to play with:");
            System.out.println("1 : 2 players");
            System.out.println("2 : plays with AI ");
            System.out.println("Enter 1 OR 2.");
            scan = new Scanner(System.in);
            choice = scan.next();

            switch (choice) {
            case "1":
                System.out.println("Name of the first player : ");
                scan = new Scanner(System.in);
                entry = scan.next();
                player1 = new Player(entry);
                System.out.println("The name of the Player1 is " + entry);
                System.out.println("Name of the second first player : ");
                scan = new Scanner(System.in);
                entry = scan.next();
                player2 = new Player(entry);
                System.out.println("The name of the Player2 is " + entry + ".");
                b = false;
                break;

            case "2":
                System.out.println("Name of the first player : ");
                scan = new Scanner(System.in);
                entry = scan.next();
                player1 = new Player(entry);
                player2 = new PlayerAI(dictionary, "AI");
                b = false;
                break;
            default:
                System.out.println("wrong entry");

            }

        }

        char p1Letter = ' ';
        char p2Letter = ' ';

        while (p1Letter == p2Letter) {
            p1Letter = player1.pick(randomLetter);
            p2Letter = player2.pick(randomLetter);
        }

        int p1LetterASCII = (int) p1Letter;
        int p2LetterASCII = (int) p2Letter;

        int highLetter = Math.min(p1LetterASCII, p2LetterASCII);

        if (highLetter == p1LetterASCII) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        sharedBag.addLetter(p1Letter);
        sharedBag.addLetter(p2Letter);

        System.out.println(player1 + " draws " + p1Letter + " and " + player2 + " draws " + p2Letter + ".\n");
        System.out.println(currentPlayer + " begins.\n");

    }

    public void play() {
        String wordPlayer;
        char letterpicked;

        while (!endGame()) {
            ArrayList<String> listwords = new ArrayList<String>();
            listwords.addAll(player1.getWordsFound().keySet());
            listwords.addAll(player2.getWordsFound().keySet());

            if (currentPlayer.getbWordFound() == false) {
                wordPlayer = "";
                System.out.println(currentPlayer + " draws twice");

                letterpicked = currentPlayer.pick(randomLetter);
                System.out.println(currentPlayer + " draws " + letterpicked);
                sharedBag.addLetter(letterpicked);
                letterpicked = currentPlayer.pick(randomLetter);
                System.out.println(currentPlayer + " draws " + letterpicked);
                sharedBag.addLetter(letterpicked);
                showGame();
                wordPlayer = currentPlayer.makeword(sharedBag, listwords);
                gameTreatment(wordPlayer);

            } else {
                wordPlayer = "";
                System.out.println(currentPlayer + " draws once");

                letterpicked = currentPlayer.pick(randomLetter);
                System.out.println(currentPlayer + " draws " + letterpicked);
                sharedBag.addLetter(letterpicked);
                showGame();
                wordPlayer = currentPlayer.makeword(sharedBag, listwords);
                gameTreatment(wordPlayer);
            }
        }
        showGame();
        System.out.println(currentPlayer + " wins!");

    }

    public void gameTreatment(String p_word) {
        if (p_word != "") {
            if (!currentPlayer.getClass().equals(PlayerAI.class)) {

                ArrayList<String> lListwords = new ArrayList<String>();
                lListwords.addAll(player1.getWordsFound().keySet());
                lListwords.addAll(player2.getWordsFound().keySet());
                // v�rifie si le mot contient des lettres valide
                if (sharedBag.verifyletters(lListwords, p_word)) {
                    boolean result = dictionary.isAword(p_word);
                    // v�rifie si ce mot est possible
                    if (result == true) {
                        // v�rifie si ce mot n'a pas que des lettres du
                        // sharedBag
                        if (!sharedBag.verifyletters(null, p_word)) {
                            // v�rifier si ce mot n'est pas le m�me que dans la
                            // liste d'un player
                            if (player1.isWordIn(p_word) || player2.isWordIn(p_word)) {
                                System.out.println("you cannot steal a word of a list");
                                currentPlayer.setbWordFound(false);
                                changePlayer();
                            } else {
                                // v�rifie si ce mot a un mot d�j� cr�e dedans
                                int indiceWord = sharedBag.verifyAllLettersOfWordIn(lListwords, p_word);

                                if (indiceWord != -1) {
                                    ArrayList<String> lListCurrentPlayer = new ArrayList<String>();
                                    lListCurrentPlayer.addAll(currentPlayer.getWordsFound().keySet());
                                    if (sharedBag.verifyAllLettersOfWordIn(lListCurrentPlayer, p_word) != (-1)) {
                                        System.out.println(currentPlayer + " find nothing. \n");
                                        changePlayer();
                                    } else {
                                        System.out.println(p_word + " is in the dictionary. \n");
                                        // remove the letters
                                        String wordUsed = lListwords.get(indiceWord);
                                        deleteLetters(p_word, wordUsed);
                                        currentPlayer.setbWordFound(true);
                                        currentPlayer.addword(p_word, dictionary.getDictionary().get(p_word));
                                    }

                                } else {
                                    System.out.println(p_word + " : le mot cr�e ne contient pas tous les lettres d'un mot.");
                                    currentPlayer.setbWordFound(false);
                                    changePlayer();
                                }
                            }

                        } else {

                            System.out.println(p_word + " is in the dictionary. \n");
                            currentPlayer.setbWordFound(true);
                            currentPlayer.addword(p_word, dictionary.getDictionary().get(p_word));
                            sharedBag.removeLetters(p_word);

                        }

                    } else {
                        System.out.println(p_word + " isn't in the dictionary. \n");
                        currentPlayer.setbWordFound(false);
                        changePlayer();
                    }

                } else {
                    System.out.println(p_word + " incorrect. \n");
                    currentPlayer.setbWordFound(false);
                    changePlayer();
                }
            } else {
                System.out.println(p_word + " is in the dictionary. \n");
                deleteLetters(p_word, currentPlayer.getWordused());
                currentPlayer.setbWordFound(true);
                currentPlayer.addword(p_word, dictionary.getDictionary().get(p_word));
            }

        } else {
            System.out.println(currentPlayer + " find nothing");
            currentPlayer.setbWordFound(false);
            changePlayer();
        }

    }

    public void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            player2.setbWordFound(false);
        } else {
            currentPlayer = player1;
            player1.setbWordFound(false);
        }
    }

    public void showGame() {
        System.out.printf(String.format("+%-40s+%-40s+%n", "", "").replace(' ', '-'));
        System.out.printf("|%-40s|%-40s|%n", player1, player2);
        System.out.printf(String.format("+%-40s+%-40s+%n", "", "").replace(' ', '-'));
        System.out.printf("|%-81s|%n", sharedBag.getlListOfLetterOnSharedBag());
        System.out.printf(String.format("+%-40s+%-40s+%n", "", "").replace(' ', '-'));

        // int sizetab = Math.max(player1.getWordsFound().size(),
        // player2.getWordsFound().size());
        // for (int i = 0; i < sizetab; i++) {
        // String wordP1 = "";
        // String wordP2 = "";
        // if (i < player1.getWordsFound().size()) {
        // wordP1 = player1.getWordsFound().get(i);
        // }
        // if (i < player2.getWordsFound().size()) {
        // wordP2 = player2.getWordsFound().get(i);
        // }
        //
        // System.out.printf("|%-40s|%-40s|%n", wordP1,wordP2);
        //
        // }
        Iterator<Entry<String, String>> iter1 = player1.getWordsFound().entrySet().iterator();
        Iterator<Entry<String, String>> iter2 = player2.getWordsFound().entrySet().iterator();
        while (iter1.hasNext() || iter2.hasNext()) {
            String wordP1 = "";
            String wordP2 = "";
            if (iter1.hasNext()) {
                Entry<String, String> e1 = iter1.next();
                wordP1 = e1.getValue();
            }
            if (iter2.hasNext()) {
                Entry<String, String> e2 = iter2.next();
                wordP2 = e2.getValue();
            }

            System.out.printf("|%-40s|%-40s|%n", wordP1, wordP2);

        }

    }

    public void deleteLetters(String p_word, String p_wordUsed) {
        player1.removeWordFound(p_wordUsed);
        player2.removeWordFound(p_wordUsed);

        char[] tabWord = p_wordUsed.toCharArray();
        String sLettersToDelete = p_word;
        for (int k = 0; k < tabWord.length; k++) {
            String s = "" + tabWord[k];
            sLettersToDelete = sLettersToDelete.replaceFirst(s, "");

        }
        if (sLettersToDelete != "") {
            sharedBag.removeLetters(sLettersToDelete);
        }

    }

    public boolean endGame() {
        if (player1.getWordsFound().size() >= 10) {
            currentPlayer = player1;
            return true;
        }
        if (player2.getWordsFound().size() >= 10) {
            currentPlayer = player2;
            return true;
        }
        return false;
    }

    public void initializeGame() {
        dictionary = new Dictionary();
        sharedBag = new SharedBag();
        randomLetter = new LetterGenerator();
    }
}