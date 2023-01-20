package monster;
import enums.*;
import javadraw.Text;

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

                this.stats[i] += (int) (Math.random() * 5) + this.level;

            }

            this.stats[9] += (int) (Math.random() * 10) + this.level;
            this.stats[10] = this.stats[9];

        }

    }

    public int[] stats() {

        return this.stats;

    }

    public ArrayList<Attack> attacks() {

        return this.attacks;

    }

    public ArrayList<Defense> defenses() {

        return this.defenses;

    }

}