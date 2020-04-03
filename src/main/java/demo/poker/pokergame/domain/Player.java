package demo.poker.pokergame.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Player {
    private String name = "";
    @Setter(value = AccessLevel.NONE)
    private Hand hand;

    public Hand getHand() {
        return hand;
    }
}
