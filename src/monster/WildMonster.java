package monster;
import javadraw.*;
import enums.*;

import java.util.ArrayList;

public class WildMonster extends Monster {

    private Screen screen;
    private Image sprite;

    public WildMonster(Screen screen, Image sprite, int level, int[] stats,
                       ArrayList<Attack> attacks, ArrayList<Defense> defenses) {

        super(level, stats, attacks, defenses, true);
        this.screen = screen;
        this.sprite = sprite;

    }

    public void center(Location location) {

        this.sprite.center(location);

    }

    public void front() {

        this.sprite.front();

    }
}
