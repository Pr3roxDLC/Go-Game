import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Player {
    int points = 0;
    Stone.Color color = Stone.Color.BLACK;

    public Player(Stone.Color color) {
        this.color = color;
    }

    public void updateScore() {
       points = (int) Arrays.stream(Main.stones).flatMap(Arrays::stream).filter(Objects::nonNull).filter(n -> n.getColor() == color).filter(Stone::isCounted).count();
    }
}
