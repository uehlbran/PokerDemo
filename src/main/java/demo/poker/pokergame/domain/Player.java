package demo.poker.pokergame.domain;

import lombok.*;

/* Currently this class has no use.
 * Was implemented as part of my plan to later add a GUI and allow actual play
 * */

@Data
@AllArgsConstructor
public final class Player {
    private String name = "";
    @Setter(value = AccessLevel.NONE)
    private Hand hand;
}
