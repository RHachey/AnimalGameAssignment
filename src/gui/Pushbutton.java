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

    protected void order() {

        this.box.front();
        this.text.front();

    }

    public void text(String text) {

        this.text.text(text);
        this.text.center(this.box.center());
        this.text.y(this.text.y() - 5);

    }

    public Rectangle box() {

        return this.box;

    }

    public void visible(boolean visible) {

        this.box.visible(visible);
        this.text.visible(visible);

    }

    public boolean visible() {

        return this.box.visible();

    }

    public void idleColor(Color color) {

        this.idleColor = color;

    }

    public void hoverCheck(Location location) {

        if (this.box.contains(location)) {

            this.box.color(activeColor);

        } else {

            this.box.color(idleColor);

        }

    }

}
