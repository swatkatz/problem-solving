package org.swati.glassdoor;

/**
 * A card object
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Card {
    private Face face;
    private Suit suit;

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }
}
