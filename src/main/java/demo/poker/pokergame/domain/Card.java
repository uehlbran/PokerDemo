package demo.poker.pokergame.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card implements Comparable<Card>{
    private SUIT suit;
    private VALUE value;

    @Override
    public int compareTo(Card o) {
        return this.value.getValue() - o.value.getValue();
    }

}
