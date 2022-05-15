import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

public class InputHandler {

    public static void handleClick(MouseEvent e){
            if(Main.board.contains(e.getPoint())){
                Arrays.stream(Main.getPoints()).flatMap(Arrays::stream).min(Comparator.comparingDouble(n -> n.distance(e.getPoint()))).ifPresent(n -> {
                    if(Main.getStones()[(n.x + 23 - Main.board.x)/30][(n.y + 23 - Main.board.y)/30]== null) {
                        Main.getStones()[(n.x + 23 - Main.board.x) / 30][(n.y + 23 - Main.board.y) / 30] = new Stone(Main.getActivePlayer().color);
                        FloodFiller.removeStonesFromCounter((n.x + 23 - Main.board.x)/30,(n.y + 23 - Main.board.y)/30);
                        Main.PLAYER_ONE.updateScore();
                        Main.PLAYER_TWO.updateScore();
                        Main.switchPlayer();
                    }
                });
            } else if (Main.SKIP_BUTTON.getRectangle().contains(e.getPoint())) {
                Main.SKIP_BUTTON.click();
            }

    }

}
