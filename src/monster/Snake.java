/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WildMonster class, an snake, an attack based desert monster
 */

package monster;
import enums.*;
import javadraw.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake extends WildMonster {

    public Snake (Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/snake.png", 0, 0, 225, 150),
                level, new int[]{15, 9, 19, 8, 7, 11, 8, 18, 17, 30, 30},
                new ArrayList<Attack>(Arrays.asList(Attack.POISONSPRAY, Attack.WATERGUN, Attack.FIREBREATH,
                        Attack.THORNBLOW)),
                new ArrayList<Defense>(Arrays.asList(Defense.POISONPARRY, Defense.TOXICSCREEN, Defense.SPARKSWERVE)),
                "snake");

    }

}
