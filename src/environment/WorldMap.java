package environment;
import javadraw.*;
import noise.NoiseGenerator;
import enums.*;

abstract class WorldMap {

    protected int width;
    protected int height;
    protected double[][] values;
    private NoiseGenerator seed;
    protected Screen screen;
    protected int imageSize;
    protected Image[][] highImages;
    protected Image[][] medImages;
    protected Image[][] lowImages;
    protected int screenWidth;
    protected int screenHeight;
    protected Location topLeft = new Location(0, 0);
    protected double range;
    protected double lower;

    public WorldMap(Screen screen, int width, int height, int imageSize) {

        this.width = width;
        this.height = height;
        this.values = new double[width][height];
        this.seed = new NoiseGenerator();
        this.screen = screen;
        this.imageSize = imageSize;
        this.screenWidth = 800 / imageSize;
        this.screenHeight = 416 / imageSize;
        this.lowImages = new Image[this.screenWidth][this.screenHeight];
        this.medImages = new Image[this.screenWidth][this.screenHeight];
        this.highImages = new Image[this.screenWidth][this.screenHeight];
        this.generateNoise();
        this.generateImages();
        this.render();

    }

    private void generateNoise() {

        double upper = 0;

        for (int i = 0; i < this.width; i++) {

            for (int j = 0; j < this.height; j++) {

                this.values[i][j] = this.seed.noise(i, j);
                upper = Math.max(this.values[i][j], upper);
                this.lower = Math.min(this.values[i][j], this.lower);

            }

        }

        this.range = upper - this.lower;

    }

    public void mapMove(Direction direction) {

        if (direction == Direction.LEFT && this.topLeft.x() > 0) {

            this.topLeft.x(this.topLeft.x() - 1);

        } else if (direction == Direction.RIGHT && this.topLeft.x() < this.width - this.screenWidth) {

            this.topLeft.x(this.topLeft.x() + 1);

        } else if (direction == Direction.UP && this.topLeft.y() > 0) {

            this.topLeft.y(this.topLeft.y() - 1);

        } else if (direction == Direction.DOWN && this.topLeft.y() < this.height - this.screenHeight) {

            this.topLeft.y(this.topLeft.y() + 1);

        }

        this.render();

    }

    public abstract void render();

    public abstract void generateImages();

}
