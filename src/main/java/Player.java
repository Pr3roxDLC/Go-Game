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
        long count = 0L;
        Stone[][] stones = Main.stones;
        for (int i = 0; i < stones.length; i++) {
            Stone[] stone = stones[i];
            for (int j = 0; j < stone.length; j++) {
                Stone n = stone[j];
                if (n != null) {
                    if (n.getColor() == color) {
                        if (n.isCounted()) {
                            count++;
                        }
                    }
                }
            }
        }
        points = (int) count;
    }
}
