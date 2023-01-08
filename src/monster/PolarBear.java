package monster;
import javadraw.*;

public class PolarBear extends Monster {

    public PolarBear(Screen screen, int level) {

        super(screen, new Image(screen, "images/monsters/polarbear.png", 0,0,128,64),
                level, new int[] {0, 13, 8, 14,18,10, 18,5, 9,30});


    }

}
