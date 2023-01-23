/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WildMonster class, a penguin, an attack based ice monster
 */

package monster;
import enums.*;
import javadraw.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Penguin extends WildMonster {

    public Penguin(Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/penguin.png", 0, 0, 200,150), level,
                new int[] {4, 19, 15, 11, 13, 11, 8, 19, 18, 25, 25},
                new ArrayList<Attack>(Arrays.asList(Attack.POISONSPRAY, Attack.WATERGUN, Attack.TIDALWAVE)),
                new ArrayList<Defense>(Arrays.asList(Defense.WATERSPHERE, Defense.WATERWALK, Defense.POISONPARRY)),
                "penguin");

    }

}
