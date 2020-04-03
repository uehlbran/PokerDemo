package demo.poker.pokergame.Domain;

import demo.poker.pokergame.domain.Card;
import demo.poker.pokergame.domain.SUIT;
import demo.poker.pokergame.domain.VALUE;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private final Card card1 = new Card(SUIT.CLUB, VALUE.ACE);
    private final Card card2 = new Card(SUIT.DIAMOND, VALUE.FIVE);

    @Test
    void givenAceAndFive_whenCompareTo_thenShouldReturnAceHigherThanFive() {
        //THEN
        assertThat(card1.compareTo(card2)).isGreaterThan(card2.compareTo(card1));
    }
}
