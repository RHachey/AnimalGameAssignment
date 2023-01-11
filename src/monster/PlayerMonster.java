package monster;
import enums.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerMonster extends Monster {

    public PlayerMonster() {

        super(0,new int[]{15,15,15,15,15,15,15,15,15,30,30},
                new ArrayList<Attack>(Arrays.asList(Attack.TIDALWAVE, Attack.THORNBLOW, Attack.FLAMINGPUNCH,
                        Attack.WATERGUN, Attack.FIREBREATH, Attack.POISONSPRAY)),
                new ArrayList<Defense>(Arrays.asList(Defense.WATERSPHERE, Defense.TOXICSCREEN, Defense.VOLCANICSHIELD,
                        Defense.WATERWALK, Defense.POISONPARRY, Defense.SPARKSWERVE)),
                false);

    }

}