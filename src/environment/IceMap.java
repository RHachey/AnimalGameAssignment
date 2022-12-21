package environment;
import javadraw.*;

public class IceMap extends WorldMap {

    public IceMap (Screen screen, int width, int height, int imageSize) {

        super(screen, width, height, imageSize);
        this.extraGeneration();

    }

    @Override
    public void extraGeneration() {

        double upper = 0;
        double lower = 0;

        for (int i = 0; i < this.width; i++) {

            for (int j = 0; j < this.height; j++) {

                upper = Math.max(this.values[i][j], upper);
                lower = Math.min(this.values[i][j], lower);

            }

        }

        double percent;

        for (int i = 0; i < this.width; i++) {

            for (int j = 0; j < this.height; j++) {

                percent = (this.values[i][j] - lower) / (upper - lower);

                if (percent >= 0.7) {

                    Image image = new Image(this.screen, "images/maps/iceHigh.png",
                            i * imageSize, j * imageSize, imageSize, imageSize);

                } else if (percent >= 0.3) {

                    Image image = new Image(this.screen, "images/maps/iceMed.png",
                            i * imageSize, j * imageSize, imageSize, imageSize);

                } else {

                    Image image = new Image(this.screen, "images/maps/iceLow.png",
                            i * imageSize, j * imageSize, imageSize, imageSize);

                }

            }

        }

    }

}
