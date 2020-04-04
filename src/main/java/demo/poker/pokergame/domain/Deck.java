package demo.poker.pokergame.domain;

import org.springframework.stereotype.Component;

import java.util.*;

/* Currently this class has no use.
* Was implemented as part of my plan to later add a GUI and allow actual play
* */

@Component
public final class Deck {
    private final List<Card> cards;
    private final Set<Card> cardSet = new HashSet<>(52);

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int remainingCards() {
        return cards.size();
    }

    public Card draw() {
        if (cards.size() < 1) throw new RuntimeException("There are no more cards in the deck");
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }

    public Deck() {
        generateCards();
        cards = new ArrayList<>(cardSet);
        shuffle();
    }

    private void generateCards() {
        for(SUIT suit : SUIT.values()) {
            for(VALUE value : VALUE.values()) {
                cardSet.add(new Card(suit, value));
            }
        }
    }
}
