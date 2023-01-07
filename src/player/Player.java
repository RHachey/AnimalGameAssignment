package player;
import javadraw.*;
public class Player {

    private Image sprite;
    private Screen screen;
    private int imageSize;

    public Player(Screen screen, int imageSize) {

        this.screen = screen;
        this.imageSize = imageSize;
        this.sprite = new Image(this.screen, "images/player/player1.png", 0, 0,
                imageSize, imageSize);
        this.center();

    }

    private void center() {

        this.sprite.moveTo((double) (832 / this.imageSize / 2 * this.imageSize),
                (double) (416 / this.imageSize / 2 * this.imageSize));

    }

    public void toFront() {

        sprite.front();

    }

}
