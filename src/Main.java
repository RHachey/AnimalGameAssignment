import enums.Direction;
import enums.Environment;
import environment.*;
import javadraw.*;
import monster.*;
import player.*;
import battle.*;

public class Main extends Window {

    DesertMap mapTest;
    Player playerOne;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean upDown = false;
    boolean downDown = false;
    boolean mouseDown = false;
    boolean inBattle = false;
    Battle currentBattle;
    Location mouseLocation;

    public void start() {

        mapTest = new DesertMap(screen,200, 200, 32);
        playerOne = new Player(screen, 32);

        while (true) {

            if (inBattle) {

                if (!currentBattle.isReallyOver()) {

                    currentBattle.checkButtons(mouseLocation, mouseDown);

                } else {

                    mapTest.visible(true);
                    inBattle = false;

                }

            } else {

                movementHandler();

            }

            screen.update();
            screen.sleep(1/60.0);

        }

    }

    public void startBattle() {

        mapTest.visible(false);
        currentBattle = new Battle(screen, new PlayerMonster(), mapTest.randomMonster(0), mapTest.environment());
        currentBattle.begin();

    }

    public void movementHandler() {

        boolean hasMoved = false;

        if (leftDown) {

            hasMoved = mapTest.mapMove(Direction.LEFT);

        }

        if (rightDown) {

            hasMoved = mapTest.mapMove(Direction.RIGHT);

        }

        if (upDown) {

            hasMoved = mapTest.mapMove(Direction.UP);

        }

        if (downDown) {

            hasMoved = mapTest.mapMove(Direction.DOWN);

        }

        if (hasMoved && Math.random() * 20 < 1) {

            inBattle = true;
            startBattle();

        }

    }

    public void mouseMove(Location location) {

        mouseLocation = location;

    }

    public void mouseDown(Location location, int button) {

        if (button == 1) {

            mouseDown = true;

        }

    }

    public void mouseUp(Location location, int button) {

        if (button == 1) {

            mouseDown = false;

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