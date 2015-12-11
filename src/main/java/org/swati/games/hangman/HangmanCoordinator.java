package org.swati.games.hangman;

import java.util.Scanner;

/**
 * The coordinator that talks to the user
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class HangmanCoordinator {
    private final Scanner stdin;
    private final HangmanAI hangmanAI;

    public HangmanCoordinator(HangmanAI hangmanAI) {
        this.stdin = new Scanner(System.in);
        this.hangmanAI = hangmanAI;
    }

    public Character getInput() {
        System.out.println(Message.ENTER_GUESS);
        String guess = stdin.nextLine();
        while (!hangmanAI.validInput(guess)) {
            System.out.println(Message.CORRECT_GUESS);
            guess = stdin.nextLine();
        }
        return guess.toLowerCase().charAt(0);
    }

    public void printOutput(String outputToBePrinted) {
        System.out.println(outputToBePrinted);
    }
}
