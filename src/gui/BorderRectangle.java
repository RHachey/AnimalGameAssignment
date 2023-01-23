/*
 * Rylan Hachey
 * 01/22/2023
 * Made it easier for myself to make a rectangle with a border
 */

package gui;
import javadraw.*;

public class BorderRectangle {

    private Rectangle border;
    private Rectangle box;

    public BorderRectangle (Screen screen, int x, int y, int width, int height, int borderThicknes, Color borderColor,
                            Color mainColor) {

        this.border = new Rectangle(screen, x, y, width, height, borderColor);
        this.box = new Rectangle(screen, x + borderThicknes, y + borderThicknes,
                width - borderThicknes * 2, height - borderThicknes * 2, mainColor);

    }

    //setter

    public void visible(boolean visible) {

        this.border.visible(visible);
        this.box.visible(visible);

    }

    //setter

    public void mainColor(Color color) {

        this.box.color(color);

    }

}
