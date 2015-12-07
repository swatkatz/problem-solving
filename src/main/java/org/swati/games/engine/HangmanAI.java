package org.swati.games.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The Hangman AI that does all the work
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class HangmanAI {
    private List<String> lexicon;
    private int guesses;
    private List<Character> correctLetters;
    private List<Character> wrongLetters;
    private Map<Character, List<Integer>> wordPosIndex;
    private String secretWord;
    private List<Character> guessedWord;

    //Initialization
    public HangmanAI(List<String> lexicon) {
        this.lexicon = lexicon;
    }

    private String chooseSecretWord() {
        Random rand = new Random();
        return lexicon.get(rand.nextInt(lexicon.size()));
    }

    private void fillWordPosIndex() {
        for (int i = 0; i < secretWord.length(); i++) {
            if (wordPosIndex.containsKey(secretWord.charAt(i))) {
                List<Integer> positions = wordPosIndex.get(secretWord.charAt(i));
                positions.add(i);
            } else {
                List<Integer> positions = new ArrayList<Integer>();
                positions.add(i);
                wordPosIndex.put(secretWord.charAt(i), positions);
            }
        }
    }

    private List<Character> initializeGuessedWord() {
        List<Character> guessedWord = new LinkedList<Character>();
        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord.add('-');
        }
        return guessedWord;
    }

    //public accessor methods
    public void initializeEngine() {
        this.guesses = 9;
        this.correctLetters = new ArrayList<Character>();
        this.wrongLetters = new ArrayList<Character>();
        this.wordPosIndex = new HashMap<Character, List<Integer>>();
        this.secretWord = chooseSecretWord();
        this.guessedWord = initializeGuessedWord();
        fillWordPosIndex();
    }

    public String getGuessedWord() {
        return buildStringFromList(guessedWord, null);
    }

    public int getGuesses() {
        return this.guesses;
    }

    public String getWrongLetters() {
        return buildStringFromList(this.wrongLetters, ',');
    }

    public String getRightLetters() {
        return buildStringFromList(this.correctLetters, ',');
    }

    public void handleGuess(Character c) {
        //correct guess
        if (wordPosIndex.containsKey(c)) {
            List<Integer> positions = wordPosIndex.get(c);
            for (Integer position : positions) {
                guessedWord.set(position, c);
            }
            correctLetters.add(c);
        } else {
            decrementChances();
            wrongLetters.add(c);
        }
    }

    public boolean gameOVer() {
        return guesses <= 0 || gameWon();
    }

    public boolean gameWon() {
        return getGuessedWord().equalsIgnoreCase(getSecretWord());
    }

    public boolean validInput(String input) {
        if (input == null || input.length() == 0 || input.length() > 1) {
            return false;
        } else if ('a' > input.charAt(0) || 'z' < input.charAt(0)) { //outside the range
            return false;
        } else if (correctLetters.contains(input.charAt(0)) || wrongLetters.contains(input.charAt(0))) {
            return false;
        }
        return true;
    }

    public String getSecretWord() {
        return this.secretWord;
    }

    //private controller methods
    private void decrementChances() {
        guesses --;
    }

    private String buildStringFromList(List<Character> givenList, Character separator) {
        StringBuilder sb = new StringBuilder();
        for (Character c : givenList) {
            sb.append(c);
            if (separator != null) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

}
