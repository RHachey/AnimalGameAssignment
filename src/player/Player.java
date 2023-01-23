/*
 * Rylan Hachey
 * 01/22/2023
 * Contains all the gui elements for the player's character
 */

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

    /**
     * Moves the sprite of the player to the middle tile of the map
     */

    private void center() {

        this.sprite.moveTo((double) (800 / this.imageSize / 2 * this.imageSize),
                (double) (416 / this.imageSize / 2 * this.imageSize));

    }

    /**
     * Moves the player to the front of the screen
     */

    public void toFront() {

        sprite.front();

    }

}
