/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the Button class, has exactly the same functionality but has a border
 */

package gui;

import javadraw.*;

public class BorderButton extends Pushbutton {

    protected Rectangle border;
    private int borderThickness;

    public BorderButton (Screen screen, String text, int x, int y, int width, int height, int borderThickness,
                         Color textColor, Color idleColor, Color activeColor, Color borderColor) {

        super(screen, text, x + borderThickness, y + borderThickness, width - borderThickness * 2,
                height - borderThickness * 2, textColor, idleColor,
                activeColor);
        this.border = new Rectangle(screen, x, y, width, height, borderColor);
        this.borderThickness = borderThickness;
        this.order();

    }

    /**
     * Sends the button to the front of the screen
     */

    public void front() {

        this.border.front();
        this.box.front();
        this.text.front();

    }

    //setter (overrides parent)

    public void visible(boolean visible) {

        this.box.visible(visible);
        this.text.visible(visible);
        this.border.visible(visible);

    }

    //setter

    public void x(double x) {

        this.border.x(x);
        this.box.x(x + this.borderThickness);
        this.text.center(this.box.center());
        this.text.y(this.text.y() - 5);

    }

    //setter

    public void y(double y) {

        this.border.y(y);
        this.box.y(y + this.borderThickness);
        this.text.center(this.box.center());
        this.text.y(this.text.y() - 5);

    }

}
