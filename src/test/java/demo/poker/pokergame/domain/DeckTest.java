package demo.poker.pokergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeckTest {
    private final Deck deck = new Deck();

    @Test
    void givenDeck_whenInstantiated_thenDeckShouldContain52Cards() {
        //WHEN
        int actual = deck.remainingCards();
        int expected = 52;
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void givenDeck_whenDraw_thenShouldRemoveAndReturnCard() {
        //WHEN
        Card card = deck.draw();
        //THEN
        assertNotNull(card);
        assertThat(deck.remainingCards()).isLessThan(52);
    }
}
