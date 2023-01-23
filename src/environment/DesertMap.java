/*
 * Rylan Hachey
 * 01/22/2023
 * Child of the WorldMap class, a desert map that uses specific images and has specific monsters to fight
 */


package environment;
import enums.Environment;
import javadraw.*;
import monster.*;

public class DesertMap extends WorldMap {

    public DesertMap (Screen screen, int width, int height, int imageSize) {

        super(screen, width, height, imageSize, Environment.DESERT);

    }

    /**
     * Updates which images should be visible based on the current location of the player and generated noise
     */

    @Override
    public void render() {

        for (int i = (int) this.topLeft.x(); i < this.screenWidth + this.topLeft.x(); i++) {

            for(int j = (int) this.topLeft.y(); j < this.screenHeight + this.topLeft.y(); j++) {

                double percent = (this.values[i][j] - this.lower) / this.range;

                if (percent >= 0.8) {

                    this.lowImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();
                    this.medImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();

                } else if (percent >= 0.6) {

                    this.lowImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();
                    this.highImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();

                } else {

                    this.highImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();
                    this.medImages[i - (int) this.topLeft.x()][j - (int) this.topLeft.y()].back();

                }

            }

        }

    }

    /**
     * Creates all the images required to create the map in full, taking up the whole screen
     */

    @Override
    public void generateImages() {

        for (int i = 0; i < this.screenWidth; i++) {

            for (int j = 0; j < this.screenHeight; j++) {

                lowImages[i][j] = new Image(this.screen, "images/maps/sandLow.png",
                        this.imageSize * i, this.imageSize * j, this.imageSize, this.imageSize);
                medImages[i][j] = new Image(this.screen, "images/maps/sandMed.png",
                        this.imageSize * i, this.imageSize * j, this.imageSize, this.imageSize);
                highImages[i][j] = new Image(this.screen, "images/maps/sandHigh.png",
                        this.imageSize * i, this.imageSize * j, this.imageSize, this.imageSize);

            }

        }

    }

    /**
     * Randomly generates a monster for the player to battle
     * @param level: the level that the monster should be
     * @return: the monster to battle
     */

    @Override
    public WildMonster randomMonster(int level) {

        return new Armadillo(this.screen, level);

    }

}
