package environment;
import enums.Environment;
import javadraw.*;
import monster.Armadillo;
import monster.WildMonster;

public class DesertMap extends WorldMap {

    public DesertMap (Screen screen, int width, int height, int imageSize) {

        super(screen, width, height, imageSize, Environment.DESERT);

    }

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

    @Override
    public WildMonster randomMonster(int level) {

        return new Armadillo(this.screen, level);

    }

}
