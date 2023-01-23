/*
 * Rylan Hachey
 * 01/22/23
 * A child of BorderButton, a button with a border that has a box with information pop up when you hover over it
 */

package gui;
import javadraw.*;

public class HoverBorderButton extends BorderButton {

    private BorderButton hoverBox;

    public HoverBorderButton (Screen screen, String text, String hoverText, int x, int y, int width, int height,
                              int borderThickness, Color textColor, Color idleColor, Color activeColor,
                              Color borderColor) {

        super(screen, text, x, y, width, height, borderThickness, textColor, idleColor, activeColor, borderColor);
        this.hoverBox = new BorderButton(screen, hoverText, x, y, width, height, borderThickness, textColor, idleColor,
                activeColor, borderColor);
        this.hoverBox.visible(false);


    }

    //setter (overrides)

    public void visible(boolean visible) {

        this.hoverBox.visible(false);
        this.box.visible(visible);
        this.text.visible(visible);
        this.border.visible(visible);

    }

    /**
     * Will change the button's color if it's hovered, and also show a little text box
     * @param location: the location of the mouse
     */

    public void hoverCheck(Location location) {

        if (this.box.contains(location)) {

            if (!((this.hoverBox.border.x() == location.x() || this.hoverBox.border.x() ==
                    location.x() - this.hoverBox.border.width()) && this.hoverBox.border.y() == location.y())) {

                this.box.color(this.activeColor);
                this.hoverBox.front();
                this.hoverBox.y(location.y());
                this.hoverBox.visible(true);

                if (this.border.width() + location.x() < 785) {

                    this.hoverBox.x(location.x());

                } else {

                    this.hoverBox.x(location.x() - this.hoverBox.border.width());

                }

            }

        } else {

            this.box.color(this.idleColor);
            this.hoverBox.visible(false);

        }

    }

}
