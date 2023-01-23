/*
* Rylan Hachey
* 1/22/2023
* All the gui elements and behaviours of a player vs cpu battle are contained in the class
* While active, essentially pauses the rest of the program
*/

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
    private Color mainColor;
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
    private BorderButton confirmButton;
    private boolean isOver = false;
    private boolean isReallyOver = false;

    public Battle (Screen screen, PlayerMonster playerMonster, WildMonster cpuMonster, Environment environment) {

        this.screen = screen;
        this.playerMonster = playerMonster;
        this.cpuMonster = cpuMonster;
        this.environment = environment;
        this.mainColor = environment.color();

    }

    /**
     * Checks if the player or monster has won the battle
     * @return if the player has won the battle
     */

    public boolean hasPlayerWon() {

        return this.playerMonster.stats()[9] != 0;

    }

    /**
     * Animates the cpu monster when it gets hit
     */

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

        for (int i = 0; i < 5; i++) {

            this.cpuMonster.sprite().move(-2, -2);
            this.screen.sleep(1/90.0);

        }

        for (int i = 0; i < 10; i++) {

            this.cpuMonster.sprite().move(2, 2);
            this.screen.sleep(1/90.0);

        }

        for (int i = 0; i < 5; i++) {

            this.cpuMonster.sprite().move(-2, -2);
            this.screen.sleep(1/90.0);

        }

    }

    /**
     * This executes the player part of the turn, like if their attack is dodged or how much damage it does
     * @param cpuDefense: the defense move the cpu is using
     */

    private void playerTurn(Defense cpuDefense) {

        double dodgeChance;

        if (cpuDefense.element() - 3 == this.currentAttack.element()) {

            dodgeChance = (this.cpuMonster.stats()[cpuDefense.type()] +
                    this.cpuMonster.stats()[cpuDefense.element()] * this.environment.mod(cpuDefense.element()))
                    / (this.cpuMonster.stats()[cpuDefense.type()] + 2 *
                    this.playerMonster.stats()[cpuDefense.type()]);

        } else {

            dodgeChance = (double) this.cpuMonster.stats()[cpuDefense.type()]
                    / (this.cpuMonster.stats()[cpuDefense.type()] + 2 *
                    this.playerMonster.stats()[cpuDefense.type()]);

        }

        //Can only dodge/block if attack and defense types are the same

        if (cpuDefense.type() == this.currentAttack.type() && Math.random() < dodgeChance) {

            //If dodged

            String slowTextString = "You used " + this.currentAttack.attackName() + "!\nThe "
                    + this.cpuMonster.name() + " used " + cpuDefense.defenseName() + "!\n";

            if (cpuDefense.type() == 6) {

                slowTextString += "The attack was blocked!";

            } else {

                slowTextString += "The attack was dodged!";

            }

            this.slowText(slowTextString);

        } else {

            //If hit

            //effective defense = element defense (double if correct type) +
            //defense type (double if correct type) + attack element defense

            double effectiveDefense = this.cpuMonster.stats()[cpuDefense.element()] *
                    environment.mod(cpuDefense.element()) + this.cpuMonster.stats()[cpuDefense.type()] +
                    this.cpuMonster.stats()[this.currentAttack.element() + 3];

            if (cpuDefense.element() - 3 == this.currentAttack.element()) {

                effectiveDefense += this.cpuMonster.stats()[cpuDefense.element()] *
                        environment.mod(cpuDefense.element());

            }

            if (cpuDefense.type() == this.currentAttack.type()) {

                effectiveDefense += this.cpuMonster.stats()[cpuDefense.type()];

            }

            //effective attack = attack type + attack element * 10

            double effectiveAttack = (this.playerMonster.stats()[this.currentAttack.element()] *
                    environment.mod(this.currentAttack.element()) +
                    this.playerMonster.stats()[this.currentAttack.type()]) * 10;
            System.out.println(effectiveDefense);
            System.out.println(effectiveAttack);
            int damage = (int) (effectiveAttack / effectiveDefense);

            this.hitAnimation();
            this.changeCpuHP(damage);
            this.slowText("You used " + this.currentAttack.attackName() + "!\nThe "
                    + this.cpuMonster.name() + " used " + cpuDefense.defenseName() + "!\nIt hits the "
                    + this.cpuMonster.name() + " doing " + damage + " damage!");

        }

    }

    /**
     * Same as the playerTurn method, but for the computer
     * @param cpuAttack: the attack move the cpu is using
     */

    private void cpuTurn(Attack cpuAttack) {

        double dodgeChance;

        if (cpuAttack.element() == this.currentDefense.element() - 3) {

            dodgeChance = (this.playerMonster.stats()[cpuAttack.type()] +
                    this.playerMonster.stats()[cpuAttack.element() + 3] * this.environment.mod(cpuAttack.element()))
                    / (this.playerMonster.stats()[cpuAttack.type()] + 2 *
                    this.cpuMonster.stats()[cpuAttack.type()]);

        } else {

            dodgeChance = (double) this.playerMonster.stats()[cpuAttack.type()]
                    / (this.playerMonster.stats()[cpuAttack.type()] + 2 *
                    this.cpuMonster.stats()[cpuAttack.type()]);

        }

        if (cpuAttack.type() == this.currentDefense.type() && Math.random() < dodgeChance) {

            String slowTextString = "The " + this.cpuMonster.name() + " used " + cpuAttack.attackName() +
                    "!\nYou used " + this.currentDefense.defenseName() + "!\n";

            if (cpuAttack.type() == 6) {

                slowTextString += "You blocked the attack!";

            } else {

                slowTextString += "You dodged the attack!";

            }

            this.slowText(slowTextString);

        } else {

            double effectiveDefense = this.playerMonster.stats()[this.currentDefense.element()] *
                    environment.mod(this.currentDefense.element()) + this.playerMonster.stats()[this.currentDefense.type()] +
                    this.playerMonster.stats()[cpuAttack.element() + 3];

            if (this.currentDefense.element() - 3 == cpuAttack.element()) {

                effectiveDefense += this.playerMonster.stats()[this.currentDefense.element()] *
                        environment.mod(this.currentDefense.element());

            }

            if (this.currentDefense.type() == cpuAttack.type()) {

                effectiveDefense += this.playerMonster.stats()[this.currentDefense.type()];

            }

            //effective attack = attack type + attack element * 10

            double effectiveAttack = (this.cpuMonster.stats()[cpuAttack.element()] *
                    environment.mod(cpuAttack.element()) +
                    this.cpuMonster.stats()[cpuAttack.type()]) * 10;
            System.out.println(effectiveDefense);
            System.out.println(effectiveAttack);
            int damage = (int) (effectiveAttack / effectiveDefense);

            this.changePlayerHP(damage);
            this.slowText("You used " + this.currentDefense.defenseName() + "!\nThe "
                    + this.cpuMonster.name() + " used " + cpuAttack.attackName() + "!\nYou are hit for "
                    + damage + " damage!");

        }

    }

    /**
     * Randomly decides the cpu's attack and defense moves as well as determining who goes first using the speed stat
     * Also calls the playerTurn and cpuTurn methods, and checks if the battle has ended
     */

    private void doRound() {

        Attack cpuCurrentAttack = this.cpuMonster.attacks().get((int)
                (Math.random() * this.cpuMonster.attacks().size()));
        Defense cpuCurrentDefense = this.cpuMonster.defenses().get((int)
                (Math.random() * this.cpuMonster.defenses().size()));

        int totalSpeed = this.playerMonster.stats()[8] + this.cpuMonster.stats()[8];

        if (Math.random() * totalSpeed < this.playerMonster.stats()[8]) {

            this.playerTurn(cpuCurrentDefense);

            if (this.cpuMonster.stats()[9] != 0) {

                screen.sleep(4);
                this.cpuTurn(cpuCurrentAttack);

            }

        } else {

            this.cpuTurn(cpuCurrentAttack);

            if (this.playerMonster.stats()[9] != 0) {

                screen.sleep(4);
                this.playerTurn(cpuCurrentDefense);

            }

        }

        if (this.playerMonster.stats()[9] == 0) {

            screen.sleep(4);
            this.slowText("You have been knocked out!\nYou have lost the battle!");
            this.isOver = true;
            this.confirmButton.text("EXIT");

        } else if (this.cpuMonster.stats()[9] == 0) {

            screen.sleep(4);
            this.slowText("The " + this.cpuMonster.name() + " has been knocked out!\n" +
                    "You have won the battle!");
            this.isOver = true;
            this.confirmButton.text("EXIT");

        }

    }

    /**
     * Checks if any of the buttons have been pressed, and executes the appropriate behaviour
     * @param mouseLocation: the location of the mouse
     * @param click: if the mouse has been clicked
     */

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

                if (!this.isOver) {

                    this.doRound();

                } else {

                    this.end();
                    this.isReallyOver = true;

                }

            }

        }

    }

    /**
     * Changes the player's HP, making sure it doesn't drop below 0
     * Also adds a cool animation
     * @param damage: the amount of damage taken
     */

    private void changePlayerHP(int damage) {

        this.playerMonster.stats()[9] = Math.max(0, this.playerMonster.stats()[9] - damage);
        int newWidth = (int) (250 * (double) this.playerMonster.stats()[9] / this.playerMonster.stats()[10]);
        int sleepNumber = (int) this.playerHP.width() - newWidth;

        for (int i = 0; i < sleepNumber; i++) {

            this.playerHP.width(this.playerHP.width() - 1);
            screen.sleep(1.0/(2 * sleepNumber));

        }

    }

    /**
     * The same thing as the changePlayerHP but for the computer
     * @param damage: the amount of damage taken
     */

    private void changeCpuHP(int damage) {

        this.cpuMonster.stats()[9] = Math.max(0, this.cpuMonster.stats()[9] - damage);
        int newWidth = (int) (250 * (double) this.cpuMonster.stats()[9] / this.cpuMonster.stats()[10]);
        int sleepNumber = (int) this.cpuHP.width() - newWidth;

        for (int i = 0; i < sleepNumber; i++) {

            this.cpuHP.width(this.cpuHP.width() - 1);
            screen.sleep(1.0/(2 * sleepNumber));

        }

    }

    /**
     * Updates the text in the text box with a cool animation
     * @param string: the text to be displayed
     */

    private void slowText(String string) {

        this.scrollText.text("");

        for (int i = 0; i < string.length(); i++) {

            this.scrollText.text(this.scrollText.text() + string.charAt(i));
            screen.sleep(1.0/string.length());

        }

    }

    /**
     * Sets up all the buttons
     */

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

    /**
     * Ends the battle, making all GUI elements invisible
     */

    public void end() {

        this.backgroundBorder.visible(false);
        this.background.visible(false);
        this.textBoxBorder.visible(false);
        this.textBox.visible(false);
        this.scrollText.visible(false);
        this.playerHPBorder.visible(false);
        this.playerHP.visible(false);
        this.playerHPtext.visible(false);
        this.cpuHPBorder.visible(false);
        this.cpuHP.visible(false);
        this.cpuHPtext.visible(false);
        this.attacksLabel.visible(false);
        this.attacksText.visible(false);
        this.defensesText.visible(false);
        this.defensesLabel.visible(false);
        this.confirmButton.visible(false);
        this.cpuMonster.visible(false);

        for (int i = 0; i < this.attackButtons.size(); i++) {

            this.attackButtons.get(i).visible(false);

        }

        for (int i = 0; i < this.defenseButtons.size(); i++) {

            this.defenseButtons.get(i).visible(false);

        }

    }

    /**
     * Begins the battle by creating all the gui elements
     */

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
        this.confirmButton = new BorderButton(this.screen, "GO!", 664, 280, 116, 116,
                8, accentColor, mainColor, Color.WHITE, accentColor);
        this.confirmButton.visible(false);
        this.cpuMonster.center(new Location(400, 195));
        this.cpuMonster.front();


    }

    //getter

    public boolean isReallyOver() {

        return this.isReallyOver;

    }

}