import java.awt.*;

public class Main {

    public static Gui gui;

    public static Button skipButton;
    public static Button giveUpButton;

    public static int timesSkipped = 0;

    public static Player playerOne = new Player(Stone.Color.BLACK);
    public static Player playerTwo = new Player(Stone.Color.WHITE);
    public static ActivePlayer activePlayer = ActivePlayer.ONE;

    public static Stone[][] stones = new Stone[19][19];
    public static Point[][] points = new Point[19][19];
    public static Rectangle board;

    public static enum ActivePlayer {
        ONE, TWO
    }

    public static void main(String[] args) {
        gui = new Gui();
    }

    public static void switchPlayer() {
        timesSkipped = 0;
        activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
        Main.skipButton.setColor(Main.getActivePlayer().color.getColor());
    }

    public static void skip() {
        timesSkipped++;
        if (timesSkipped == 2) {
            giveUp();
        }
        activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
        Main.skipButton.setColor(Main.getActivePlayer().color.getColor());
    }

    public static void giveUp() {
        System.exit(-1);
    }

    public static Player getActivePlayer() {
        return activePlayer == ActivePlayer.ONE ? playerOne : playerTwo;
    }
}
