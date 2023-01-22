import enums.Direction;
import enums.Environment;
import environment.*;
import gui.*;
import javadraw.*;
import monster.*;
import player.*;
import battle.*;
import java.util.*;

public class Main extends Window {

    int currentMap;
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
    BorderRectangle menuBackground;
    BorderButton tundraSelector;
    BorderButton desertSelector;
    BorderButton forestSelector;

    public void start() {

        maps.add(new IceMap(screen, 200, 200, 32));
        maps.add(new DesertMap(screen, 200, 200, 32));
        maps.get(1).visible(false);
        currentMap = 0;
        playerOne = new Player(screen, 32);
        BorderButton mapSelector = new BorderButton(screen, "CHANGE MAP", 10, 10, 150,50,
                8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK);
        menuBackground = new BorderRectangle(screen, 0, 0, 800, 416, 15, Color.BLACK,
                maps.get(currentMap).environment().color());
        tundraSelector = new BorderButton(screen, "TUNDRA", 30, 30, 237, 60, 8,
                Color.BLACK, Environment.ICE.color(), Color.WHITE, Color.BLACK);
        desertSelector = new BorderButton(screen, "DESERT", 282, 30, 236, 60, 8,
                Color.BLACK, Environment.DESERT.color(), Color.WHITE, Color.BLACK);
        forestSelector = new BorderButton(screen, "FOREST", 533, 30, 237, 60, 8,
                Color.BLACK, Environment.FOREST.color(), Color.WHITE, Color.BLACK);
        mapSelectorShow(false);


        while (true) {

            if (inBattle) {

                if (!currentBattle.isReallyOver()) {

                    currentBattle.checkButtons(mouseLocation, mouseDown);

                } else {

                    maps.get(currentMap).visible(true);
                    inBattle = false;

                }

            } else {

                movementHandler();
                mapSelector.hoverCheck(mouseLocation);

                if (mouseDown && mapSelector.box().contains(mouseLocation)) {

                    mapSelectorShow(true);
                    inMapSelector = true;
                    mouseDown = false;

                    while (inMapSelector) {

                        tundraSelector.hoverCheck(mouseLocation);
                        desertSelector.hoverCheck(mouseLocation);
                        forestSelector.hoverCheck(mouseLocation);

                        if (mouseDown && tundraSelector.box().contains(mouseLocation)) {

                            maps.get(0).visible(true);
                            maps.get(1).visible(false);
                            currentMap = 0;
                            inMapSelector = false;

                        } else if (mouseDown && desertSelector.box().contains(mouseLocation)) {

                            maps.get(0).visible(false);
                            maps.get(1).visible(true);
                            currentMap = 1;
                            inMapSelector = false;

                        }

                    }

                    mapSelector.idleColor(maps.get(currentMap).environment().color());
                    mapSelectorShow(false);

                }

            }

            screen.update();
            screen.sleep(1/60.0);

        }

    }

    public void mapSelectorShow(boolean visible) {

        menuBackground.mainColor(maps.get(currentMap).environment().color());
        menuBackground.visible(visible);
        desertSelector.visible(visible);
        tundraSelector.visible(visible);
        forestSelector.visible(visible);

    }

    public void startBattle() {

        maps.get(currentMap).visible(false);
        currentBattle = new Battle(screen, new PlayerMonster(), maps.get(currentMap).randomMonster(0),
                maps.get(currentMap).environment());
        currentBattle.begin();

    }

    public void movementHandler() {

        boolean hasMoved = false;

        if (leftDown) {

            hasMoved = maps.get(currentMap).mapMove(Direction.LEFT);

        }

        if (rightDown) {

            hasMoved = maps.get(currentMap).mapMove(Direction.RIGHT);

        }

        if (upDown) {

            hasMoved = maps.get(currentMap).mapMove(Direction.UP);

        }

        if (downDown) {

            hasMoved = maps.get(currentMap).mapMove(Direction.DOWN);

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