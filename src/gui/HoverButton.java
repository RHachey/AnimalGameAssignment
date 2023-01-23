package gui;

import javadraw.Color;
import javadraw.Location;
import javadraw.Screen;

public class HoverButton extends Pushbutton {

    private Pushbutton hoverBox;

    public HoverButton (Screen screen, String text, String hoverText, int x, int y, int width, int height,
                        Color textColor, Color idleColor, Color activeColor) {

        super(screen, text, x, y, width, height, textColor, idleColor, activeColor);
        this.hoverBox = new Pushbutton(screen, hoverText, x, y, width, height, textColor, idleColor, activeColor);
        this.hoverBox.visible(false);

    }

    //setter (overrides)

    public void visible(boolean visible) {

        this.hoverBox.visible(false);
        this.box.visible(visible);
        this.text.visible(visible);

    }

    /**
     * Will change the button's color if it's hovered, and also show a little text box
     * @param location: the location of the mouse
     */

    public void hoverCheck(Location location) {

        if (this.box.contains(location)) {

            if (this.hoverBox.box.x() != location.x() || this.hoverBox.box.y() != location.y()) {

                this.box.color(this.activeColor);
                this.hoverBox.order();
                this.hoverBox.x(location.x());
                this.hoverBox.y(location.y());
                this.hoverBox.visible(true);

            }

        } else {

            this.box.color(this.idleColor);
            this.hoverBox.visible(false);

        }

    }

}
