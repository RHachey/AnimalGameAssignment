import enums.Direction;
import environment.*;
import gui.BorderButton;
import gui.Pushbutton;
import javadraw.*;
import monster.*;
import player.*;
import battle.*;
import java.util.*;

public class Main extends Window {

    WorldMap mapTest;
    ArrayList<WorldMap> maps = new ArrayList<WorldMap>();
    Player playerOne;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean upDown = false;
    boolean downDown = false;
    boolean mouseDown = false;
    boolean inBattle = false;
    boolean inMapSelector = false;
    Battle currentBattle;
    Location mouseLocation = new Location(0, 0);

    public void start() {

        maps.add(new IceMap(screen, 200, 200, 32));
        maps.get(0).visible(false);
        maps.add(new DesertMap(screen, 200, 200, 32));
        mapTest = maps.get(1);
        playerOne = new Player(screen, 32);
        BorderButton mapSelector = new BorderButton(screen, "CHANGE MAP", 10, 10, 150,50,
                8, Color.BLACK, mapTest.environment().color(), Color.WHITE, Color.BLACK);

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
                mapSelector.hoverCheck(mouseLocation);

                if (mouseDown && mapSelector.box().contains(mouseLocation)) {

                    Rectangle menuBorder = new Rectangle (screen, 0, 0, 800, 416, Color.BLACK);
                    Rectangle menuBackground = new Rectangle (screen, 15, 15, 770, 386,
                            mapTest.environment().color());

                }

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

        System.out.println(key);

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