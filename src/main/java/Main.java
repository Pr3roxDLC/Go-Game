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

    public static State GAME_STATE = State.ON_GOING;

    public enum State{
        ON_GOING,
        PLAYER_ONE_WON,
        PLAYER_TWO_WON,
        DRAW
    }

    public enum ActivePlayer {
        ONE, TWO
    }

    public static void main(String[] args) {
        gui = new Gui();
        for (int i = 0; i < stones.length; i++) {
            Stone[] stones1 = stones[i];
            for (int j = 0; j < stones1.length; j++) {
                stones[i][j] = new Stone(Stone.Color.BLACK);
                if(Math.random() > 0.5){
                    stones[i][j] = new Stone(Stone.Color.WHITE);
                }
            }
        }
        stones[5][4] = null;
    }

    public static void switchPlayer() {
        timesSkipped = 0;
        activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
        Main.SKIP_BUTTON.setColor(Main.getActivePlayer().color.getColor());
        if(isBoardFilled())showEndScreen();
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
        showEndScreen();
    }

    public static void showEndScreen(){
        System.out.println(PLAYER_ONE.points + " " + PLAYER_TWO.points);
        if(PLAYER_ONE.points > PLAYER_TWO.points){
            GAME_STATE = State.PLAYER_ONE_WON;
        }else if(PLAYER_ONE.points < PLAYER_TWO.points){
            GAME_STATE = State.PLAYER_TWO_WON;
        }else{
            GAME_STATE = State.DRAW;
        }
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

    public static boolean isBoardFilled() {
        for (int i = 0; i < stones.length; i++) {
            Stone[] stoneLine = stones[i];
            for (int j = 0; j < stoneLine.length; j++) {
                Stone stone = stoneLine[j];
                if (stone == null) return false;
            }
        }
        return true;
    }



}
