package demo.poker.pokergame.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
public class Hand implements Comparable<Hand>{
    @Setter(value = AccessLevel.NONE)
    //Cards are always stored from lowest value to highest
    private final Set<Card> cards = new LinkedHashSet<>();
    private RANK rank;

    public void calculateRank() {
        //1. Determine
        Map<SUIT, Integer> kinds = new HashMap<>();

    }

    public void addCard(Card card) {
        if (cards.size() >= 5) throw new RuntimeException("There is no more room for new cards");
        cards.add(card);
    }

    public void removeCard(Card card) {
        if (cards.size() <= 0) throw new RuntimeException("There are no cards to be removed");
        cards.remove(card);
    }

    @Override
    public int compareTo(Hand o) {
        return 0;
    }

    public boolean hasStraightFlush() {
//       boolean consecutiveValues = false;
//       Integer lastValue = null;
//       Map<SUIT, Integer> pairs = new HashMap<>();
//       for(Card card : cards) {
//           if (!pairs.containsKey(card.getSuit())) {
//               pairs.put(card.getSuit(), 1);
//           } else {
//               pairs.put(card.getSuit(), pairs.get(card.getSuit()) + 1);
//           }
//           if (lastValue == null) lastValue = card.getValue().getValue();
//           else if (lastValue + 1)
        return false;
    }
}
