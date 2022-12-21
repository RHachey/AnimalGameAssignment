package environment;
import javadraw.*;
import noise.NoiseGenerator;

abstract class WorldMap {

    protected int width;
    protected int height;
    protected double values[][];
    private NoiseGenerator seed;
    protected Screen screen;
    protected int imageSize;

    public WorldMap(Screen screen, int width, int height, int imageSize) {

        this.width = width;
        this.height = height;
        this.values = new double[width][height];
        this.seed = new NoiseGenerator();
        this.screen = screen;
        this.imageSize = imageSize;
        this.generateNoise();

    }

    private void generateNoise() {

        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                values[i][j] = seed.noise(i, j);

            }

        }

    }

    public abstract void extraGeneration();

}
