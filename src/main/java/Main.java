import java.awt.*;

public class Main {

    public static Gui gui;


    public static Button SKIP_BUTTON;

    private static int timesSkipped = 0;

    public static Player PLAYER_ONE = new Player(Stone.Color.BLACK);
    public static Player PLAYER_TWO = new Player(Stone.Color.WHITE);
    public static ActivePlayer activePlayer = ActivePlayer.ONE;

    private static final Stone[][] stones = new Stone[19][19];
    private static final Point[][] points = new Point[19][19];
    public static Rectangle board;

    public enum ActivePlayer {
        ONE, TWO
    }

    public static void main(String[] args) {
        gui = new Gui();
    }

    public static void switchPlayer() {
        timesSkipped = 0;
        activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
        Main.SKIP_BUTTON.setColor(Main.getActivePlayer().color.getColor());
    }

    public static void skip() {
        timesSkipped++;
        if (timesSkipped == 2) {
            giveUp();
        }
        activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
        Main.SKIP_BUTTON.setColor(Main.getActivePlayer().color.getColor());
    }

    public static void giveUp() {
        System.exit(-1);
    }

    public static Player getActivePlayer() {
        return activePlayer == ActivePlayer.ONE ? PLAYER_ONE : PLAYER_TWO;
    }

    public static Stone[][] getStones() {
        return stones;
    }

    public static Point[][] getPoints() {
        return points;
    }
}
