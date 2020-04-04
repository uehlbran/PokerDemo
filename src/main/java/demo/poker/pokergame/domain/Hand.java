package demo.poker.pokergame.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public final class Hand implements Comparable<Hand>{
    @Setter(value = AccessLevel.NONE)
    private final List<Card> cards = new ArrayList<>();
    private RANK rank;

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
        if (rank.getValue() == o.rank.getValue()) {
            if (rank == RANK.STRAIGHT_FLUSH || rank == RANK.STRAIGHT) {
                return this.highestCardTieBreaker() - o.highestCardTieBreaker();
            } else if (rank == RANK.FOUR_OF_A_KIND || rank == RANK.THREE_OF_A_KIND) {
                return this.threeAndFourOfAKindTieBreaker(rank.getValue()) - o.threeAndFourOfAKindTieBreaker(o.rank.getValue());
            } else if (rank == RANK.FULL_HOUSE) {
                return this.threeAndFourOfAKindTieBreaker(3) - o.threeAndFourOfAKindTieBreaker(3);
            } else {
                Collection<Integer> coll1;
                Collection<Integer> coll2;
                if (rank == RANK.FLUSH || rank == RANK.HIGH_CARD) {
                    coll1 = this.highCardTieBreaker();
                    coll2 = o.highCardTieBreaker();
                } else {
                    coll1 = this.pairAndTwoPairTieBreaker();
                    coll2 = o.pairAndTwoPairTieBreaker();
                }
                Iterator<Integer> coll1Iterator = coll1.iterator();
                Iterator<Integer> coll2Iterator = coll2.iterator();
                while (coll1Iterator.hasNext() && coll2Iterator.hasNext()) {
                    int coll1Value = coll1Iterator.next();
                    int coll2Value = coll2Iterator.next();
                    if (coll1Value != coll2Value) {
                        return coll1Value - coll2Value;
                    }
                }
                return 0;
            }
        }
        return this.getRank().getValue() - o.getRank().getValue();
    }

    private boolean isConsecutive() {
        for(int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getValue() + 1 != cards.get(i).getValue()) return false;
        }
        return true;
    }

    private Map<SUIT, Integer> countSuits() {
        Map<SUIT, Integer> suits = new HashMap<>();
        for(Card card : cards) {
            if (suits.containsKey(card.getSuit())) suits.put(card.getSuit(), suits.get(card.getSuit()) + 1);
            else suits.put(card.getSuit(), 1);
        }
        return suits;
    }

    private Map<Integer, Integer> countValues() {
        Map<Integer, Integer> values = new HashMap<>();
        for (Card card : cards) {
            if (values.containsKey(card.getValue())) values.put(card.getValue(), values.get(card.getValue()) + 1);
            else values.put(card.getValue(), 1);
        }
        return values;
    }

    public void calculateHand() {
       Map<Integer, Integer> values = countValues();
       Map<SUIT, Integer> suits = countSuits();
       if (suits.containsValue(5) && isConsecutive()) {
           rank = RANK.STRAIGHT_FLUSH; //highest card tie breaker
       } else if (values.containsValue(4)) {
           rank = RANK.FOUR_OF_A_KIND; //four of a kind tie breaker
       } else if (values.containsValue(3) && values.containsValue(2)) {
           rank = RANK.FULL_HOUSE; //three of a kind and full house tie breaker
       } else if (countSuits().containsValue(5)) {
           rank = RANK.FLUSH; //high card tie breaker
       } else if (isConsecutive()) {
           rank = RANK.STRAIGHT; //highest card tie breaker
       } else if (values.containsValue(3)) {
           rank = RANK.THREE_OF_A_KIND; //three of a kind and full house tie breaker
       } else if (values.values().stream().filter(v -> v == 2).count() == 2) {
           rank = RANK.TWO_PAIR; //pair and two pair tie breaker
       } else if (values.containsValue(2)) {
           rank = RANK.PAIR; //pair and two pair tie breaker
       } else {
           rank = RANK.HIGH_CARD; //high card tie breaker
       }
    }

    private Integer highestCardTieBreaker() {
        return Collections.max(cards).getValue();
    }

    private Integer threeAndFourOfAKindTieBreaker(int count) {
        for (Map.Entry<Integer, Integer> set : countValues().entrySet()) {
            if (set.getValue() == count) return set.getKey() * count;
        }
        throw new RuntimeException("Unable to find correct values");
    }

    private Collection<Integer> highCardTieBreaker() {
        return countValues().keySet().stream().sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
    }

    private Collection<Integer> pairAndTwoPairTieBreaker() {
        List<Integer> results = new ArrayList<>(countValues().values());
        Collections.sort(results);
        Collections.reverse(results);
        List<Integer> values = results.stream().filter(v -> v == 2).distinct().collect(Collectors.toList());
        values.addAll(results.stream().filter(v -> !values.contains(v)).collect(Collectors.toList()));
        return values;
    }
}
