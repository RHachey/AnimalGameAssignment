/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WildMonster class, an armadillo, a defense based desert monster
 */

package monster;
import enums.*;
import javadraw.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Armadillo extends WildMonster {

    public Armadillo (Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/armadillo.png", 0, 0, 256, 128),
                level, new int[]{11, 0, 15, 16, 14, 19, 18, 8, 9, 35, 35},
                new ArrayList<Attack>(Arrays.asList(Attack.POISONSPRAY, Attack.THORNBLOW, Attack.FIREBREATH)),
                new ArrayList<Defense>(Arrays.asList(Defense.WATERSPHERE, Defense.TOXICSCREEN, Defense.VOLCANICSHIELD,
                        Defense.POISONPARRY)),
                "armadillo");

    }

}
