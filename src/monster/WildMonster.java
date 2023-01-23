/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the Monster class, a cpu controlled monster that has an image to be displayed when in a battle, as well as
 * random(ish) stats and a type name
 */

package monster;
import javadraw.*;
import enums.*;

import java.util.ArrayList;

public class WildMonster extends Monster {

    private Screen screen;
    private Image sprite;
    private String name;

    public WildMonster(Screen screen, Image sprite, int level, int[] stats,
                       ArrayList<Attack> attacks, ArrayList<Defense> defenses, String name) {

        super(level, stats, attacks, defenses, true);
        this.screen = screen;
        this.sprite = sprite;
        this.name = name;

    }

    //setter

    public void visible(boolean visible) {

        this.sprite.visible(visible);

    }

    //getter

    public Image sprite() {

        return sprite;

    }

    //getter

    public String name() {

        return name;

    }

    public void center(Location location) {

        this.sprite.center(location);

    }

    public void front() {

        this.sprite.front();

    }
}
