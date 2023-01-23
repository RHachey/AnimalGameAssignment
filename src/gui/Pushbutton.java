/*
 * Rylan Hachey
 * 01/22/2023
 * A rectangle that can be used as a button, as it adds additional behaviours to it, as well as makes it easier
 * to handle then a bunch of independent shapes
 */

package gui;

import javadraw.*;

public class Pushbutton {

    private Screen screen;
    protected Rectangle box;
    protected Text text;
    private Color idleColor;
    private Color activeColor;

    public Pushbutton(Screen screen, String text, int x, int y, int width, int height,
                      Color textColor, Color idleColor, Color activeColor) {

        this.screen = screen;
        this.box = new Rectangle(screen, x, y, width, height, idleColor);
        this.text = new Text(screen, text, 0, 0);
        this.idleColor = idleColor;
        this.activeColor = activeColor;
        this.text.font("Monospaced");
        this.text.bold(true);
        this.text.size(15);
        this.text.color(textColor);
        this.text.center(this.box.center());
        this.text.y(this.text.y() - 5);

    }

    /**
     * Correctly orders the elements of the button
     */

    protected void order() {

        this.box.front();
        this.text.front();

    }

    //getter

    public String text() {

        return this.text.text();

    }

    //setter

    public void text(String text) {

        this.text.text(text);
        this.text.center(this.box.center());
        this.text.y(this.text.y() - 5);

    }

    //getter

    public Rectangle box() {

        return this.box;

    }

    //setter

    public void visible(boolean visible) {

        this.box.visible(visible);
        this.text.visible(visible);

    }

    //getter

    public boolean visible() {

        return this.box.visible();

    }

    //setter

    public void idleColor(Color color) {

        this.idleColor = color;
        this.box.color(idleColor);

    }

    /**
     * If hovered, the button will change color, letting the user know it has functionality
     * @param location: the location of the mouse
     */

    public void hoverCheck(Location location) {

        if (this.box.contains(location)) {

            this.box.color(activeColor);

        } else {

            this.box.color(idleColor);

        }

    }

}
