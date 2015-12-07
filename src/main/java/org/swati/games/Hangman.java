package org.swati.games;

import java.util.Arrays;
import java.util.List;

import org.swati.games.coordinator.HangmanCoordinator;
import org.swati.games.engine.HangmanAI;
import org.swati.games.model.Message;

/**
 * A Hangman interpretation of the game shell
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Hangman implements Game {
    private final HangmanCoordinator coordinator;
    private HangmanAI hangmanAI;

    public Hangman() {
        List<String> lexicon = Arrays.asList("geography", "cat", "yesterday", "java", "truck", "opportunity",
                "fish", "token", "transportation", "bottom", "apple", "cake",
                "remote", "pocket", "terminology", "arm", "cranberry", "tool",
                "caterpillar", "spoon", "watermelon", "laptop", "toe", "toad",
                "fundamental", "capitol", "garbage", "anticipate", "apple");
        hangmanAI = new HangmanAI(lexicon);
        coordinator = new HangmanCoordinator(hangmanAI);
    }

    public void playGame() {
        hangmanAI.initializeEngine();
        coordinator.printOutput(Message.START_MESSAGE);
        while (!gameOver()) {
            coordinator.printOutput(Message.GUESS + hangmanAI.getGuessedWord());
            coordinator.printOutput(Message.TRIES + hangmanAI.getGuesses());
            coordinator.printOutput(Message.WRONG + hangmanAI.getWrongLetters());
            coordinator.printOutput(Message.RIGHT + hangmanAI.getRightLetters());
            Character c = coordinator.getInput();
            hangmanAI.handleGuess(c);
        }
        if (gameWon()) {
            coordinator.printOutput(Message.WIN_MESSAGE);
        } else {
            coordinator.printOutput(Message.LOSE_MESSAGE);
        }
        coordinator.printOutput(Message.REVEAL + hangmanAI.getSecretWord());
    }

    public boolean gameOver() {
        return hangmanAI.gameOVer();
    }

    public boolean gameWon() {
        return hangmanAI.gameWon();
    }

    public boolean gameLost() {
        return !hangmanAI.gameWon();
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.playGame();
    }

}
