import enums.*;
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
    PlayerMonster playerMonster;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean upDown = false;
    boolean downDown = false;
    boolean mouseDown = false;
    boolean inBattle = false;
    boolean inMenu = false;
    Battle currentBattle;
    Location mouseLocation = new Location(0, 0);
    BorderRectangle menuBackground;
    BorderButton tundraSelector;
    BorderButton desertSelector;
    BorderButton forestSelector;
    BorderButton escapeStats;
    BorderButton pointsDisplay;
    ArrayList<BorderButton> statButtons = new ArrayList<BorderButton>();
    int currentPoints = 0;
    int allTimePoints = 0;

    public void start() {

        playerMonster = new PlayerMonster();
        maps.add(new IceMap(screen, 200, 200, 32));
        maps.add(new DesertMap(screen, 200, 200, 32));
        maps.add(new ForestMap(screen, 200, 200, 32));
        maps.get(1).visible(false);
        maps.get(2).visible(false);
        currentMap = 0;
        playerOne = new Player(screen, 32);
        BorderButton statIncreaser = new BorderButton(screen, "INCREASE STATS", 640, 10, 150, 50,
                8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK);
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
        setUpStatIncreaser();
        statIncreaserShow(false);


        while (true) {

            if (inBattle) {

                if (!currentBattle.isReallyOver()) {

                    currentBattle.checkButtons(mouseLocation, mouseDown);

                } else {

                    maps.get(currentMap).visible(true);
                    inBattle = false;

                    if (currentBattle.hasPlayerWon()) {

                        currentPoints += 3;
                        allTimePoints += 3;
                        playerMonster.stats()[10] += 2;
                        pointsDisplay.text("AVAILABLE POINTS : " + currentPoints);

                    }

                    playerMonster.stats()[9] = playerMonster.stats()[10];

                }

            } else {

                movementHandler();
                mapSelector.hoverCheck(mouseLocation);
                statIncreaser.hoverCheck(mouseLocation);

                if (mouseDown && mapSelector.box().contains(mouseLocation)) {

                    mapSelectorShow(true);
                    inMenu = true;
                    mouseDown = false;

                    while (inMenu) {

                        tundraSelector.hoverCheck(mouseLocation);
                        desertSelector.hoverCheck(mouseLocation);
                        forestSelector.hoverCheck(mouseLocation);

                        if (mouseDown && tundraSelector.box().contains(mouseLocation)) {

                            maps.get(0).visible(true);
                            maps.get(1).visible(false);
                            maps.get(2).visible(false);
                            currentMap = 0;
                            inMenu = false;

                        } else if (mouseDown && desertSelector.box().contains(mouseLocation)) {

                            maps.get(0).visible(false);
                            maps.get(1).visible(true);
                            maps.get(2).visible(false);
                            currentMap = 1;
                            inMenu = false;

                        } else if (mouseDown && forestSelector.box().contains(mouseLocation)) {

                            maps.get(0).visible(false);
                            maps.get(1).visible(false);
                            maps.get(2).visible(true);
                            currentMap = 2;
                            inMenu = false;

                        }

                    }

                    mapSelector.idleColor(maps.get(currentMap).environment().color());
                    statIncreaser.idleColor(maps.get(currentMap).environment().color());
                    mapSelectorShow(false);

                } else if (mouseDown && statIncreaser.box().contains(mouseLocation)) {

                    statIncreaserShow(true);
                    inMenu = true;
                    mouseDown = false;

                    while (inMenu) {

                        escapeStats.hoverCheck(mouseLocation);

                        if (mouseDown && escapeStats.box().contains(mouseLocation)) {

                            statIncreaserShow(false);
                            inMenu = false;

                        }

                        for (int i = 0; i < statButtons.size(); i++) {

                            statButtons.get(i).hoverCheck(mouseLocation);

                            if (mouseDown && currentPoints != 0 && statButtons.get(i).box().contains(mouseLocation)) {

                                currentPoints -= 1;
                                playerMonster.stats()[i] += 1;
                                String currentText =  statButtons.get(i).text();
                                statButtons.get(i).text(currentText.substring(0, currentText.length() - 3) +
                                        playerMonster.stats()[i] + ")");
                                pointsDisplay.text("AVAILABLE POINTS : " + currentPoints);
                                mouseDown = false;

                            }

                        }

                    }

                }

            }

            screen.update();
            screen.sleep(1/60.0);

        }

    }

    public void statIncreaserShow(boolean visible) {

        menuBackground.mainColor(maps.get(currentMap).environment().color());
        menuBackground.visible(visible);
        pointsDisplay.idleColor(maps.get(currentMap).environment().color());
        pointsDisplay.visible(visible);
        escapeStats.idleColor(maps.get(currentMap).environment().color());
        escapeStats.visible(visible);

        for (int i = 0; i < statButtons.size(); i++) {

            statButtons.get(i).idleColor(maps.get(currentMap).environment().color());
            statButtons.get(i).visible(visible);

        }

    }

    public void setUpStatIncreaser() {

        pointsDisplay = new BorderButton(screen, "POINTS AVAILABLE: 0", 30, 30, 740, 47,
                8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK);
        statButtons.add(new BorderButton(screen, "INCREASE FIRE ATTACK (CURRENT 15)", 30, 92, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE WATER ATTACK (CURRENT 15)", 408, 92, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE POISON ATTACK (CURRENT 15)", 30, 154, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE FIRE DEFENSE (CURRENT 15)", 408, 154, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE WATER DEFENSE (CURRENT 15)", 30, 216, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE POISON DEFENSE (CURRENT 15)", 408, 216, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE STRENGTH (CURRENT 15)", 30, 278, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE FINESSE (CURRENT 15)", 408, 278, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        statButtons.add(new BorderButton(screen, "INCREASE SPEED (CURRENT 15)", 30, 340, 362,
                47, 8, Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK));
        escapeStats = new BorderButton(screen, "EXIT", 408, 340, 362, 47, 8,
                Color.BLACK, maps.get(currentMap).environment().color(), Color.WHITE, Color.BLACK);

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
        currentBattle = new Battle(screen, playerMonster, maps.get(currentMap).randomMonster(allTimePoints % 10),
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