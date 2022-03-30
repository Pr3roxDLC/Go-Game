import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

public class InputHandler {

    public static void handleClick(MouseEvent e){
            if(Main.board.contains(e.getPoint())){
                Arrays.stream(Main.points).flatMap(Arrays::stream).min(Comparator.comparingDouble(n -> n.distance(e.getPoint()))).ifPresent(n -> {
                    Main.stones[(n.x + 23 - Main.board.x)/30][(n.y + 23 - Main.board.y)/30] = new Stone(Main.getActivePlayer().color);
                    Main.switchPlayer();
                });
            }
    }

}
