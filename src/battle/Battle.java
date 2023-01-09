package battle;
import enums.*;
import monster.*;

public class Battle {

    private PlayerMonster playerMonster;
    private WildMonster cpuMonster;
    private Environment environment;

    public Battle (PlayerMonster playerMonster, WildMonster cpuMonster, Environment environment) {

        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;

    }

}
