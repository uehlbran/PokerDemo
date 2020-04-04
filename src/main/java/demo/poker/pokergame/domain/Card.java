package demo.poker.pokergame.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Card implements Comparable<Card>{
    private final SUIT suit;
    private final VALUE value;

    @Override
    public int compareTo(Card o) {
        return this.value.getValue() - o.value.getValue();
    }

    public int getValue() {
        return value.getValue();
    }

    public SUIT getSuit() {
        return suit;
    }

}
