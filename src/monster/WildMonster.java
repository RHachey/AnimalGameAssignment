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

    public void visible(boolean bool) {

        this.sprite.visible(false);

    }

    public Image sprite() {

        return sprite;

    }

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
