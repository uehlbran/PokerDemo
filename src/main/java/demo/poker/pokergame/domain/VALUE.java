package demo.poker.pokergame.domain;

public enum VALUE {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(11),
    KING(12),
    ACE(13);

    private final int value;

    public int getValue() {
        return value;
    }

    VALUE(final int value) {
        this.value = value;
    }
}
