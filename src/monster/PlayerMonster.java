package monster;
import enums.*;

public class PlayerMonster extends Monster {

    public PlayerMonster() {

        super(0,new int[]{15,15,15,15,15,15,15,15,15,30}, new Attack[]{Attack.TIDALWAVE},
                new Defense[]{Defense.WATERSPHERE}, true);

    }

}