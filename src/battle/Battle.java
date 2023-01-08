package battle;
import enums.*;
import monster.*;

public class Battle {

    private Monster playerMonster;
    private Monster cpuMonster;
    private Environment environment;

    public Battle (Monster playerMonster, Monster cpuMonster, Environment environment) {

        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;

    }

}
