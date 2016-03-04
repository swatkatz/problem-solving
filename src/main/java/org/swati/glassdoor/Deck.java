package org.swati.glassdoor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Describes a list of cards
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Deck {
    private Card[] cards;

    public Deck() {
        Face[] faces = Face.values();
        Suit[] suits = Suit.values();
        cards = new Card[faces.length * suits.length];

        int i = 0;
        for (Face face : faces) {
            for (Suit suit : suits) {
                Card card = new Card(face, suit);
                cards[i] = card;
                i++;
            }
        }
    }

    private void printCards() {
        for (Card card : cards) {
            System.out.println(card.getSuit() + " " + card.getFace());
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int index = random.nextInt(cards.length);
            swap(index, i);
        }
    }

    private void swap(int random, int given) {
        Card givenCard = cards[given];
        cards[given] = cards[random];
        cards[random] = givenCard;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        deck.printCards();
    }
}
