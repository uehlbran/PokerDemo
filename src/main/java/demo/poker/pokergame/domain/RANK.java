package demo.poker.pokergame.domain;

public enum RANK {
    HIGH_CARD(0), //No combinations just value of highest card
    PAIR(1), //Hand contains two of the same suit
    TWO_PAIR(2), //Hand contains 2 PAIR's
    THREE_OF_A_KIND(3), //Hand contains 3 cards that all have the same value
    STRAIGHT(4), //Hand contains 5 cards with consecutive values
    FLUSH(5), //Hand contains five cards of the same suit
    FULL_HOUSE(6), //Hand contains a THREE_OF_A_KIND and a PAIR (3, 3, 3, SPADE, SPADE)
    FOUR_OF_A_KIND(7), //Hand contains 4 cards with the same value
    STRAIGHT_FLUSH(8); //Hand 5 cards all of which have the same SUIT and are consecutive values

    private final int value;

    public int getValue() {
        return value;
    }

    RANK(final int value) {
        this.value = value;
    }
}
