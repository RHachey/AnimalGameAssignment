/*
 * Rylan Hachey
 * 01/22/2023
 * A monster that can participate in battles with stats, attacks, and defenses
 */

package monster;
import enums.*;
import java.util.ArrayList;

public abstract class Monster {

    private int level;
    //fireAttack, waterAttack, poisonAttack, fireDefense, waterDefense, poisonDefense,
    // strength, finesse, speed, currentHP, maxHP
    private int[] stats;
    private ArrayList<Attack> attacks;
    private ArrayList<Defense> defenses;

    public Monster(int level, int[] stats, ArrayList<Attack> attacks, ArrayList<Defense> defenses, boolean doRandom) {

        this.level = level;
        this.stats = stats;
        this.attacks = attacks;
        this.defenses = defenses;

        if (doRandom) {

            for (int i = 0; i < 9; i++) {

                this.stats[i] += (int) (Math.random() * 3) + this.level;

            }

            this.stats[9] += (int) (Math.random() * 6) + this.level;
            this.stats[10] = this.stats[9];

        }

    }

    //getter

    public int[] stats() {

        return this.stats;

    }

    //getter

    public ArrayList<Attack> attacks() {

        return this.attacks;

    }

    //getter

    public ArrayList<Defense> defenses() {

        return this.defenses;

    }

}