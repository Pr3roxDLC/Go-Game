import java.awt.*;

public class Main {

    public static Gui gui;

    public static Player playerOne = new Player(Stone.Color.BLACK);
    public static Player playerTwo = new Player(Stone.Color.WHITE);
    public static ActivePlayer activePlayer = ActivePlayer.ONE;

    public static Stone[][] stones = new Stone[19][19];
    public static Point[][] points = new Point[19][19];
    public static Rectangle board;

    public static enum ActivePlayer{
        ONE,TWO
    }

    public static void main(String[] args) {
         gui = new Gui();
   }

   public static void switchPlayer(){
       activePlayer = activePlayer == ActivePlayer.ONE ? ActivePlayer.TWO : ActivePlayer.ONE;
   }

   public static Player getActivePlayer(){
        return activePlayer == ActivePlayer.ONE ? playerOne : playerTwo;
   }
}
