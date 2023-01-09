package monster;
import javadraw.*;
import enums.*;

public class PolarBear extends WildMonster {

    public PolarBear(Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/polarbear.png", 0,0,128,64),
                level, new int[] {0, 13, 8, 14,18,10, 18,5, 9,30},
                new Attack[]{Attack.WATERGUN, Attack.TIDALWAVE, Attack.THORNBLOW},
                new Defense[] {Defense.VOLCANICSHIELD, Defense.TOXICSCREEN, Defense.WATERSPHERE, Defense.WATERWALK});


    }

}
