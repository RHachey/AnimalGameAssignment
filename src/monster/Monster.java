package monster;
import javadraw.*;

public class Monster {

    private Image sprite;
    private Screen screen;
    private int level;
    //fireAttack, waterAttack, poisonAttack, fireDefense, waterDefense, poisonDefense,
    // strength, finesse, speed, hp
    private int[] stats;

    public Monster(Screen screen, Image sprite, int level, int[] stats) {

        this.screen = screen;
        this.sprite = sprite;
        this.level = level;
        this.stats = stats;

        for (int i = 0; i < 9; i++) {

            stats[i] += (int) (Math.random() * 5) + this.level;

        }

        stats[9] += (int) (Math.random() * 10) + this.level;

    }

}