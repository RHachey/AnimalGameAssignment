package monster;
import javadraw.*;
import enums.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PolarBear extends WildMonster {

    public PolarBear(Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/polarbear.png", 0,0,256,128),
                level, new int[] {0, 13, 8, 14,18,10, 18,5, 9,30,30},
                new ArrayList<Attack>(Arrays.asList(Attack.WATERGUN, Attack.TIDALWAVE, Attack.THORNBLOW)),
                new ArrayList<Defense>(Arrays.asList(Defense.VOLCANICSHIELD, Defense.TOXICSCREEN,
                        Defense.WATERSPHERE, Defense.WATERWALK)));


    }

}
