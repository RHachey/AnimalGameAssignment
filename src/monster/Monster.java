package monster;
import javadraw.*;
import enums.*;

abstract class Monster {

    private int level;
    //fireAttack, waterAttack, poisonAttack, fireDefense, waterDefense, poisonDefense,
    // strength, finesse, speed, hp
    private int[] stats;
    private Attack[] attacks;
    private Defense[] defenses;

    public Monster(int level, int[] stats, Attack[] attacks, Defense[] defenses, boolean doRandom) {

        this.level = level;
        this.stats = stats;
        this.attacks = attacks;
        this.defenses = defenses;

        if (doRandom) {

            for (int i = 0; i < 9; i++) {

                stats[i] += (int) (Math.random() * 5) + this.level;

            }

            stats[9] += (int) (Math.random() * 10) + this.level;

        }

    }

}