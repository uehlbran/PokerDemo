package demo.poker.pokergame.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void givenFiveCardsOfTheSameSuitWithConsecutiveValues_whenIsStraightFlush_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.CLUB, VALUE.SEVEN),
                new Card(SUIT.CLUB, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.NINE));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.STRAIGHT_FLUSH, hand.getRank());
    }

    @Test
    void givenFourCardsWithSameValues_whenIsFourOfAKind_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.SIX));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.FOUR_OF_A_KIND, hand.getRank());
    }

    @Test
    void givenThreeCardsWithTheSameValueAndTheRemainingCardsFormAPair_whenIsFullHouse_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.CLUB, VALUE.SIX));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.FULL_HOUSE, hand.getRank());
    }

    @Test
    void givenFiveCardsOfTheSameSuitAndNotAHigherRank_whenIsFlush_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.SIX));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.FLUSH, hand.getRank());
    }

    @Test
    void givenFiveCardsWithConsecutiveValuesAndNotAHigherRank_whenIsStraight_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.FOUR),
                new Card(SUIT.SPADE, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.STRAIGHT, hand.getRank());
    }

    @Test
    void givenThreeCardsWithTheSameValuesAndNotAHigherRank_whenIsThreeOfAKind_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.SPADE, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.THREE_OF_A_KIND, hand.getRank());
    }

    @Test
    void givenTwoSetsOfCardsWhereEachSetHasSameValuesAndNotAHigherRank_whenIsTwoPair_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.SPADE, VALUE.SEVEN),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.TWO_PAIR, hand.getRank());
    }

    @Test
    void givenTwoCardsWithTheSameValuesAndNotAHigherRank_whenIsPair_thenShouldReturnTrue() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.SPADE, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertEquals(RANK.PAIR, hand.getRank());
    }

    @Test
    void giveCardsThatDontMatchAnyOtherRanks_whenCalculateHand_thenShouldSetRankToHIGH_CARD() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.KING),
                new Card(SUIT.SPADE, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        hand.calculateHand();
        //THEN
        assertThat(hand.getRank()).isEqualTo(RANK.HIGH_CARD);
    }

    @Test
    void givenHand_whenHighestCardTieBreaker_thenShouldReturnHighestCardInHand() {
        //GIVEN
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.KING),
                new Card(SUIT.SPADE, VALUE.THREE),
                new Card(SUIT.CLUB, VALUE.SIX),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        Integer actual = hand.highestCardTieBreaker();
        //THEN
        assertEquals(VALUE.KING.getValue(), actual);
    }

    @Test
    void givenHand_whenFourOfAKindTieBreaker_thenShouldReturnTheSumOfTheFourCards() {
        //GIVEN
        int expected = 32;
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.EIGHT),
                new Card(SUIT.SPADE, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.EIGHT),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        Integer actual = hand.threeAndFourOfAKindTieBreaker(4);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void givenHand_whenHighCardTieBreaker_thenShouldReturnCollectionOfAllCardsSortedInDescendingOrder() {
        //GIVEN
        Collection<Integer> expected = Arrays.asList(11, 9, 7, 5, 3);

        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.NINE),
                new Card(SUIT.CLUB, VALUE.SEVEN),
                new Card(SUIT.SPADE, VALUE.FIVE),
                new Card(SUIT.CLUB, VALUE.THREE),
                new Card(SUIT.DIAMOND, VALUE.QUEEN));
        hand.getCards().addAll(cards);
        //WHEN
        Collection<Integer> actual = hand.highCardTieBreaker();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void givenHand_whenThreeOfAKindTieBreaker_thenShouldReturnSumOfCardsUsedInThreeOfAKind() {
        //GIVEN
        int expected = 24;
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.EIGHT),
                new Card(SUIT.SPADE, VALUE.EIGHT),
                new Card(SUIT.CLUB, VALUE.FIVE),
                new Card(SUIT.DIAMOND, VALUE.SEVEN));
        hand.getCards().addAll(cards);
        //WHEN
        Integer actual = hand.threeAndFourOfAKindTieBreaker(3);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void givenHand_whenPairOrTwoTieBreaker_thenShouldReturnCollectionOfPairsFollowedByRemainingCardsInDescendingOrder() {
        //GIVEN
        Collection<Integer> expected = Arrays.asList(11, 7, 4);
        List<Card> cards = Arrays.asList(
                new Card(SUIT.DIAMOND, VALUE.QUEEN),
                new Card(SUIT.CLUB, VALUE.SEVEN),
                new Card(SUIT.SPADE, VALUE.SEVEN),
                new Card(SUIT.CLUB, VALUE.FOUR),
                new Card(SUIT.DIAMOND, VALUE.QUEEN));
        hand.getCards().addAll(cards);
        //WHEN
        Collection<Integer> actual = hand.highCardTieBreaker();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void givenHand_whenCustomTestCases1_thenHandTwoShouldWin() {
        //GIVEN
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        //SETUP hand1
        hand1.addCard(new Card(SUIT.HEART, VALUE.TWO));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.THREE));
        hand1.addCard(new Card(SUIT.SPADE, VALUE.FIVE));
        hand1.addCard(new Card(SUIT.CLUB, VALUE.NINE));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.KING));
        //SETUP hand 2
        hand2.addCard(new Card(SUIT.CLUB, VALUE.TWO));
        hand2.addCard(new Card(SUIT.HEART, VALUE.THREE));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.FOUR));
        hand2.addCard(new Card(SUIT.CLUB, VALUE.EIGHT));
        hand2.addCard(new Card(SUIT.HEART, VALUE.ACE));
        //WHEN
        hand1.calculateHand();
        hand2.calculateHand();
        //THEN
        assertThat(hand2.compareTo(hand1)).isGreaterThanOrEqualTo(1);
    }

    @Test
    void givenHand_whenCustomTestCases2_thenHandOneShouldWin() {
        //GIVEN
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        //SETUP hand1
        hand1.addCard(new Card(SUIT.HEART, VALUE.TWO));
        hand1.addCard(new Card(SUIT.SPADE, VALUE.FOUR));
        hand1.addCard(new Card(SUIT.CLUB, VALUE.FOUR));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.TWO));
        hand1.addCard(new Card(SUIT.HEART, VALUE.FOUR));
        //SETUP hand 2
        hand2.addCard(new Card(SUIT.SPADE, VALUE.TWO));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.EIGHT));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.ACE));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.QUEEN));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.THREE));
        //WHEN
        hand1.calculateHand();
        hand2.calculateHand();
        //THEN
        assertThat(hand1.compareTo(hand2)).isGreaterThanOrEqualTo(1);
    }

    @Test
    void givenHand_whenCustomTestCases3_thenHandOneShouldWin() {
        //GIVEN
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        //SETUP hand1
        hand1.addCard(new Card(SUIT.HEART, VALUE.TWO));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.THREE));
        hand1.addCard(new Card(SUIT.SPADE, VALUE.FIVE));
        hand1.addCard(new Card(SUIT.CLUB, VALUE.NINE));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.KING));
        //SETUP hand 2
        hand2.addCard(new Card(SUIT.CLUB, VALUE.TWO));
        hand2.addCard(new Card(SUIT.HEART, VALUE.THREE));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.FOUR));
        hand2.addCard(new Card(SUIT.CLUB, VALUE.EIGHT));
        hand2.addCard(new Card(SUIT.HEART, VALUE.KING));
        //WHEN
        hand1.calculateHand();
        hand2.calculateHand();
        //THEN
        assertThat(hand1.compareTo(hand2)).isGreaterThanOrEqualTo(1);
    }

    @Test
    void givenHand_whenCustomTestCases4_thenShouldTie() {
        //GIVEN
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        //SETUP hand1
        hand1.addCard(new Card(SUIT.HEART, VALUE.TWO));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.THREE));
        hand1.addCard(new Card(SUIT.SPADE, VALUE.FIVE));
        hand1.addCard(new Card(SUIT.CLUB, VALUE.NINE));
        hand1.addCard(new Card(SUIT.DIAMOND, VALUE.KING));
        //SETUP hand 2
        hand2.addCard(new Card(SUIT.DIAMOND, VALUE.TWO));
        hand2.addCard(new Card(SUIT.HEART, VALUE.THREE));
        hand2.addCard(new Card(SUIT.CLUB, VALUE.FIVE));
        hand2.addCard(new Card(SUIT.SPADE, VALUE.NINE));
        hand2.addCard(new Card(SUIT.HEART, VALUE.KING));
        //WHEN
        hand1.calculateHand();
        hand2.calculateHand();
        //THEN
        assertThat(hand1.compareTo(hand2)).isGreaterThanOrEqualTo(0);
    }
}
