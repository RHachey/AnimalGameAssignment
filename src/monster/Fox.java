/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WildMonster class, a fox, an attack based forest monster
 */

package monster;
import enums.*;
import javadraw.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Fox extends WildMonster {

    public Fox (Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/fox.png", 0, 0, 150, 150),
                level, new int[]{18, 15, 10, 14, 12, 8, 8, 18, 17, 33, 33},
                new ArrayList<Attack>(Arrays.asList(Attack.FIREBREATH, Attack.FLAMINGPUNCH, Attack.WATERGUN,
                        Attack.POISONSPRAY)),
                new ArrayList<Defense>(Arrays.asList(Defense.SPARKSWERVE, Defense.POISONPARRY, Defense.WATERWALK,
                        Defense.VOLCANICSHIELD)),
                "fox");

    }

}
