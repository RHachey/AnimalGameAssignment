import enums.Direction;
import environment.IceMap;
import javadraw.*;

public class Main extends Window {

    IceMap mapTest;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean upDown = false;
    boolean downDown = false;

    public void start() {

        mapTest = new IceMap(screen,100, 100, 32);

        while (true) {

            movementHandler();
            screen.update();
            screen.sleep(1/60.0);

        }

    }

    public void movementHandler() {

        if (leftDown) {

            mapTest.mapMove(Direction.LEFT);

        }

        if (rightDown) {

            mapTest.mapMove(Direction.RIGHT);

        }

        if (upDown) {

            mapTest.mapMove(Direction.UP);

        }

        if (downDown) {

            mapTest.mapMove(Direction.DOWN);

        }

    }

    public void keyDown(Key key) {

        if (key == Key.LEFT) {

            leftDown = true;

        } else if (key == Key.RIGHT) {

            rightDown = true;

        } else if (key == Key.UP) {

            upDown = true;

        } else if (key == Key.DOWN) {

            downDown = true;

        }

    }

    public void keyUp(Key key) {

        if (key == Key.LEFT) {

            leftDown = false;

        } else if (key == Key.RIGHT) {

            rightDown = false;

        } else if (key == Key.UP) {

            upDown = false;

        } else if (key == Key.DOWN) {

            downDown = false;

        }

    }

    public static void main(String[] args) {

        Window.open(800,416,"test");

    }

}