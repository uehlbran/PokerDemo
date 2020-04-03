package demo.poker.pokergame.Domain;

import demo.poker.pokergame.domain.Card;
import demo.poker.pokergame.domain.Hand;
import demo.poker.pokergame.domain.SUIT;
import demo.poker.pokergame.domain.VALUE;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {
    private Hand hand = new Hand();


    @Test
    void givenCard_whenAddCard_thenShouldAddCardToHand() {
        //GIVEN
        Card card = new Card(SUIT.CLUB, VALUE.FIVE);
        //WHEN
        hand.addCard(card);
        //THEN
        assertTrue(hand.getCards().contains(card));
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @Test
    void givenCard_whenAddCard_thenShouldThrowErrorIfHandAlreadyHasFiveCards() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.ACE),
                new Card(SUIT.CLUB, VALUE.FOUR),
                new Card(SUIT.CLUB, VALUE.KING),
                new Card(SUIT.CLUB, VALUE.NINE));
        hand.getCards().addAll(cards);
        //THEN
        assertThat(hand.getCards().size()).isEqualTo(5);
        assertThrows(RuntimeException.class, () -> hand.addCard(new Card(SUIT.CLUB, VALUE.SEVEN)));
    }

    @Test
    void givenHand_whenRemoveCard_thenShouldRemoveAndReturnCard() {
        //GIVEN
        Card card = new Card(SUIT.CLUB, VALUE.SEVEN);
        hand.addCard(card);
        //WHEN
        hand.removeCard(card);
        //THEN
        assertThat(hand.getCards().size()).isEqualTo(0);
    }

    @Test
    void givenHand_whenRemoveCard_thenShouldThrowExceptionWhenEmpty() {
        //THEN
        assertThrows(RuntimeException.class, () -> hand.removeCard(new Card(SUIT.CLUB, VALUE.SEVEN)));
    }
}
