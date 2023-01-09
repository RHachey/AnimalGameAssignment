package gui;

import javadraw.*;

public class Pushbutton {

    private Screen screen;
    private Rectangle box;
    private Color idleColor;
    private Color activeColor;

    public Pushbutton(Screen screen, int x, int y, int width, int height, Color idleColor, Color activeColor) {

        this.screen = screen;
        this.box = new Rectangle(screen, x, y, width, height, idleColor);
        this.idleColor = idleColor;
        this.activeColor = activeColor;

    }

}
