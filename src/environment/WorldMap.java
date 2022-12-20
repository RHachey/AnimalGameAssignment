package environment;
import javadraw.*;

abstract class WorldMap {

    private int width;
    private int height;
    private String lowPath;
    private String medPath;
    private String highPath;

    public WorldMap(int width, int height, String lowPath, String medPath, String highPath, Screen screen) {

        this.width = width;
        this.height = height;
        this.lowPath = lowPath;
        this.medPath = medPath;
        this.highPath = highPath;

    }

    public abstract void extraGeneration();

}
