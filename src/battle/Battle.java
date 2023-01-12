package battle;
import enums.*;
import gui.*;
import monster.*;
import javadraw.*;
import java.util.ArrayList;

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
    private Rectangle attacksLabel;
    private Rectangle defensesLabel;
    private Text attacksText;
    private Text defensesText;
    private ArrayList<Pushbutton> attackButtons;
    private ArrayList<Pushbutton> defenseButtons;
    private Attack currentAttack;
    private Defense currentDefense;
    private Pushbutton confirmButton;

    public Battle (Screen screen, PlayerMonster playerMonster, WildMonster cpuMonster, Environment environment) {

        this.screen = screen;
        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;

    }

    private void hitAnimation() {

        for (int i = 0; i < 5; i++) {

            this.cpuMonster.sprite().move(2,-2);
            this.screen.sleep(1/90.0);

        }

        for (int i = 0; i < 10; i++) {

            this.cpuMonster.sprite().move(-2,2);
            this.screen.sleep(1/90.0);

        }

        for (int i = 0; i < 5; i++) {

            this.cpuMonster.sprite().move(2, -2);
            this.screen.sleep(1/90.0);

        }

    }

    private void playerTurn(Defense cpuDefense) {

        int effectiveDefense = this.cpuMonster.stats()[this.currentAttack.type()];
        double dodgeChance;

        if (cpuDefense.element() == this.currentAttack.element()) {

            dodgeChance = (double) this.cpuMonster.stats()[cpuDefense.type()]
                    / (this.cpuMonster.stats()[cpuDefense.type()] + 1.5 * this.playerMonster.stats()[cpuDefense.type()]);

        } else {

            dodgeChance = (double) this.cpuMonster.stats()[cpuDefense.type()]
                    / (this.cpuMonster.stats()[cpuDefense.type()] + 2 * this.playerMonster.stats()[cpuDefense.type()]);

        }

        if (cpuDefense.type() == this.currentAttack.type() && Math.random() < dodgeChance) {

            String slowTextString = "You used " + this.currentAttack.attackName() + "!\nThe "
                    + this.cpuMonster.name() + " used " + cpuDefense.defenseName() + "!\n";

            if (cpuDefense.type() == 6) {

                slowTextString += "The attack was blocked!";

            } else {

                slowTextString += "The attack was dodged";

            }

            this.slowText(slowTextString);

        } else {

            this.hitAnimation();
            this.changeCpuHP(1);
            this.slowText("bozo");

        }

    }

    private void cpuTurn(Attack cpuAttack) {



    }

    private void doRound() {

        Attack cpuCurrentAttack = this.cpuMonster.attacks().get((int)
                (Math.random() * this.cpuMonster.attacks().size()));
        Defense cpuCurrentDefense = this.cpuMonster.defenses().get((int)
                (Math.random() * this.cpuMonster.defenses().size()));
        System.out.println(cpuCurrentAttack.attackName() + " " + cpuCurrentDefense.defenseName());

        int totalSpeed = this.playerMonster.stats()[7] + this.cpuMonster.stats()[7];

        if (Math.random() * totalSpeed < this.playerMonster.stats()[7]) {

            this.playerTurn(cpuCurrentDefense);
            this.cpuTurn(cpuCurrentAttack);

        }

    }

    public void checkButtons(Location mouseLocation, boolean click) {

        for (int i = 0; i < this.attackButtons.size(); i++) {

            this.attackButtons.get(i).hoverCheck(mouseLocation);

            if (click && this.attackButtons.get(i).box().contains(mouseLocation)) {

                this.attacksText.text("CURRENT ATTACK:\n" + this.playerMonster.attacks().get(i).attackName());
                this.currentAttack = this.playerMonster.attacks().get(i);

            }

        }

        for (int i = 0; i < this.defenseButtons.size(); i++) {

            this.defenseButtons.get(i).hoverCheck(mouseLocation);

            if (click && this.defenseButtons.get(i).box().contains(mouseLocation)) {

                this.defensesText.text("CURRENT DEFENSE:\n" + this.playerMonster.defenses().get(i).defenseName());
                this.currentDefense = this.playerMonster.defenses().get(i);

            }

        }

        this.confirmButton.visible(this.currentAttack != null && this.currentDefense != null);

        if (this.confirmButton.visible()) {

            this.confirmButton.hoverCheck(mouseLocation);

            if (click && this.confirmButton.box().contains(mouseLocation)) {

                this.doRound();

            }

        }

    }

    private void changePlayerHP(int damage) {

        this.playerMonster.stats()[9] = Math.max(0, this.playerMonster.stats()[9] - damage);
        int newWidth = (int) (250 * (double) this.playerMonster.stats()[9] / this.playerMonster.stats()[10]);
        int sleepNumber = (int) this.playerHP.width() - newWidth;

        for (int i = 0; i < sleepNumber; i++) {

            this.playerHP.width(this.playerHP.width() - 1);
            screen.sleep(1.0/(2 * sleepNumber));

        }

    }

    private void changeCpuHP(int damage) {

        this.cpuMonster.stats()[9] = Math.max(0, this.cpuMonster.stats()[9] - damage);
        int newWidth = (int) (250 * (double) this.cpuMonster.stats()[9] / this.cpuMonster.stats()[10]);
        int sleepNumber = (int) this.cpuHP.width() - newWidth;

        for (int i = 0; i < sleepNumber; i++) {

            this.cpuHP.width(this.cpuHP.width() - 1);
            screen.sleep(1.0/(2 * sleepNumber));

        }

    }

    private void slowText(String string) {

        this.scrollText.text("");

        for (int i = 0; i < string.length(); i++) {

            this.scrollText.text(this.scrollText.text() + string.charAt(i));
            screen.sleep(1.0/string.length());

        }

    }

    private void setUpButtons() {

        attackButtons = new ArrayList<Pushbutton>();
        defenseButtons = new ArrayList<Pushbutton>();

        for (int i = 0; i < this.playerMonster.attacks().size(); i++) {

            this.attackButtons.add(new Pushbutton(this.screen, this.playerMonster.attacks().get(i).attackName(),
                    20, 70 + i * 35, 240, 25, mainColor, accentColor, Color.GRAY));

        }

        for (int i = 0; i < this.playerMonster.defenses().size(); i++) {

            this.defenseButtons.add(new Pushbutton(this.screen, this.playerMonster.defenses().get(i).defenseName(),
                    540, 70 + i * 35, 240, 25, mainColor, accentColor, Color.GRAY));

        }

    }

    public void begin() {

        this.backgroundBorder = new Rectangle(this.screen, 0, 0, 800, 416, accentColor);
        this.background = new Rectangle(this.screen, 10, 10, 780, 396, mainColor);
        this.textBoxBorder = new Rectangle(this.screen, 20, 280, 634, 116, accentColor);
        this.textBox = new Rectangle(this.screen, 28, 288, 618, 100, mainColor);
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
        this.attacksLabel = new Rectangle (this.screen, 20, 20 , 240, 40, accentColor);
        this.attacksText = new Text(this.screen, "CURRENT ATTACK:\nNONE", 140, 25, mainColor, null,
                "Monospaced", 12, "center", true, false, false,
                false, 0, true);
        this.defensesLabel = new Rectangle (this.screen, 540, 20 , 240, 40, accentColor);
        this.defensesText = new Text(this.screen, "CURRENT DEFENSE:\nNONE", 660, 25, mainColor, null,
                "Monospaced", 12, "center", true, false, false,
                false, 0, true);
        this.setUpButtons();
        this.confirmButton = new Pushbutton(this.screen, "GO!", 664, 280, 116, 116,
                mainColor, accentColor, Color.GRAY);
        this.confirmButton.visible(false);
        this.cpuMonster.center(background.center());
        this.cpuMonster.front();


    }

}
