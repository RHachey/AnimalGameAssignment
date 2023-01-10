package battle;
import enums.*;
import gui.*;
import monster.*;
import javadraw.*;

public class Battle {

    private Screen screen;
    private PlayerMonster playerMonster;
    private WildMonster cpuMonster;
    private Environment environment;
    private Rectangle background;
    private Pushbutton confirmButton;


    public Battle (Screen screen, PlayerMonster playerMonster, WildMonster cpuMonster, Environment environment) {

        this.screen = screen;
        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;

    }

    public void begin() {

        this.background = new Rectangle(this.screen, 10, 10, 780, 396,
                new Color(225,225,255));
        this.cpuMonster.center(background.center());
        this.cpuMonster.front();

    }

}
