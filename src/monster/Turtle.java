/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WildMonster class, a turtle, a defense based forest monster
 */

package monster;
import enums.*;
import javadraw.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Turtle extends WildMonster {

    public Turtle (Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/turtle.png", 0, 0, 256, 128),
                level, new int[]{12, 10, 0, 19, 15, 12, 5, 19, 4, 38, 38},
                new ArrayList<Attack>(Arrays.asList(Attack.FLAMINGPUNCH, Attack.TIDALWAVE)),
                new ArrayList<Defense>(Arrays.asList(Defense.VOLCANICSHIELD, Defense.WATERSPHERE, Defense.TOXICSCREEN,
                        Defense.SPARKSWERVE)),
                "turtle");

    }

}
