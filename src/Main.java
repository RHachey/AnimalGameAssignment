import enums.Direction;
import enums.Environment;
import environment.IceMap;
import javadraw.*;
import monster.PlayerMonster;
import monster.PolarBear;
import player.*;
import battle.*;

public class Main extends Window {

    IceMap mapTest;
    Player playerOne;
    boolean leftDown = false;
    boolean rightDown = false;
    boolean upDown = false;
    boolean downDown = false;

    boolean inBattle = false;
    Battle currentBattle;

    public void start() {

        mapTest = new IceMap(screen,200, 200, 32);
        playerOne = new Player(screen, 32);

        while (true) {

            if (inBattle) {



            } else {

                movementHandler();

            }

            screen.update();
            screen.sleep(1/10.0);

        }

    }

    public void startBattle() {

        currentBattle = new Battle(screen, new PlayerMonster(), new PolarBear(screen, 0), Environment.ICE);
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

        Window.open(832,416,"test");

    }

}