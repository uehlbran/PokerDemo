package demo.poker.pokergame.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/* Currently this class has no use.
 * Was implemented as part of my plan to later add a GUI and allow actual play.
 * */

public class Game {
    private static final Game INSTANCE = new Game();
    private static final Set<Player> PLAYERS = new LinkedHashSet<>();
    private static final Deck DECK = new Deck();
    private Game() {}
    public static Game getInstance() {
        return INSTANCE;
    }
    public static void deal() {
        //Every player is given 5 cards, one at a time, in a round-robin fashion
        for(int i = 0; i < 5; i++) {
            for(Player p : PLAYERS) {
                p.getHand().addCard(DECK.draw());
            }
        }
    }
    public static void addPlayer(Player player) {
        PLAYERS.add(player);
    }
    public static void removePlayer(Player player) {
        PLAYERS.remove(player);
    }
    public static void draw(Player player) {
        player.getHand().addCard(DECK.draw());
    }
    public static void shuffle() {
        DECK.shuffle();
    }
}
