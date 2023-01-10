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
    private Rectangle backgroundBorder;
    private Rectangle background;
    private Rectangle textBox;
    private Rectangle textBoxBorder;
    private Color mainColor = new Color(225, 225, 255);
    private Color accentColor = Color.BLACK;
    private Rectangle cpuHP;
    private Rectangle cpuHPBorder;
    private Text cpuHPtext;
    private Rectangle playerHP;
    private Rectangle playerHPBorder;
    private Text playerHPtext;
    private Text scrollText;

    public Battle (Screen screen, PlayerMonster playerMonster, WildMonster cpuMonster, Environment environment) {

        this.screen = screen;
        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;

    }

    public void slowText(String string) {

        for (int i = 0; i < string.length(); i++) {

            this.scrollText.text(this.scrollText.text() + string.charAt(i));
            screen.sleep(1.0/string.length());

        }

    }

    public void begin() {

        this.backgroundBorder = new Rectangle(this.screen, 0, 0, 800, 416, accentColor);
        this.background = new Rectangle(this.screen, 10, 10, 780, 396, mainColor);
        this.textBoxBorder = new Rectangle(this.screen, 20, 280, 760, 116, accentColor);
        this.textBox = new Rectangle(this.screen, 28, 288, 744, 100, mainColor);
        this.scrollText = new Text(this.screen, "", 36, 288, accentColor, null,
                "Monospaced", 25, "left", true, false, false,
                false, 0, true);
        this.playerHPBorder = new Rectangle(this.screen, 270, 20, 260,40, accentColor);
        this.playerHP = new Rectangle(this.screen, 275, 25, 250, 30, Color.GREEN);
        this.playerHPtext = new Text(this.screen, "PLAYER", 280, 26, Color.GRAY, null,
                "Monospaced", 20, "left", true, false, false,
                false, 0, true);
        this.cpuHPBorder = new Rectangle(this.screen, 270, 70, 260,40, accentColor);
        this.cpuHP = new Rectangle(this.screen, 275, 75, 250, 30, Color.GREEN);
        this.cpuHPtext = new Text(this.screen, "MONSTER", 280, 76, Color.GRAY, null,
                "Monospaced", 20, "left", true, false, false,
                false, 0, true);
        this.cpuMonster.center(background.center());
        this.cpuMonster.front();
        this.slowText("THIS IS A TEST\nDOES NEW LINE WORK?\nIT DID :D");

    }

}
