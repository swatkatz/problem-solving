package org.swati.games.model;

/**
 * Messages to be presented to the user
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Message {
    public static final String WIN_MESSAGE = "Congrats! you won";
    public static final String LOSE_MESSAGE = "Sorry, you lost. Better luck next time";
    public static final String START_MESSAGE = "We are going to start the hangman game. You have only 9 chances to guess incorrectly.";
    public static final String ENTER_GUESS = "Enter a character as your guess: ";
    public static final String CORRECT_GUESS = "Make sure to enter only 1 char that you have not entered previously between a-z";
    public static final String GUESS = "Guess : ";
    public static final String TRIES = "Tries : ";
    public static final String WRONG = "Wrong : ";
    public static final String RIGHT = "Right : ";
    public static final String REVEAL = "The word is : ";
}
