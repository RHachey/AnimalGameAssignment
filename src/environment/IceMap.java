package environment;
import javadraw.*;
public class IceMap extends WorldMap {

    public IceMap (int width, int height, Screen screen) {

        super(width, height, "/environment/iceLow.png",
                "/environment/iceMed.png",
                "/environment/iceHigh.png", screen);

    }

    @Override
    public void extraGeneration() {



    }

}
