package monster;
import javadraw.*;
import enums.*;

public class WildMonster extends Monster {

    private Screen screen;
    private Image sprite;

    public WildMonster(Screen screen, Image sprite, int level, int[] stats, Attack[] attacks, Defense[] defenses) {

        super(level, stats, attacks, defenses, true);
        this.screen = screen;
        this.sprite = sprite;

    }

}
