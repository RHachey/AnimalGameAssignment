import environment.IceMap;
import javadraw.*;

public class Main extends Window {

    public void start() {

        IceMap mapTest = new IceMap(screen,50, 25, 32);

        while (true) {

            screen.update();
            screen.sleep(1/60.0);

        }

    }
    public static void main(String[] args) {

        Window.open(800,416,"test");

    }

}