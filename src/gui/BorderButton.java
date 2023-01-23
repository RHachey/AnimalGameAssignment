package gui;

import javadraw.*;

public class BorderButton extends Pushbutton {

    Rectangle border;

    public BorderButton (Screen screen, String text, int x, int y, int width, int height, int borderThickness,
                         Color textColor, Color idleColor, Color activeColor, Color borderColor) {

        super(screen, text, x + borderThickness, y + borderThickness, width - borderThickness * 2,
                height - borderThickness * 2, textColor, idleColor,
                activeColor);
        this.border = new Rectangle(screen, x, y, width, height, borderColor);
        this.order();

    }

    public void visible(boolean visible) {

        this.box.visible(visible);
        this.text.visible(visible);
        this.border.visible(visible);

    }

}