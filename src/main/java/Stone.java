import java.awt.*;

public class Stone {

    private final Color color;
    private boolean counted = true;

    public Stone(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public enum Color{

        BLACK(java.awt.Color.BLACK), WHITE(java.awt.Color.WHITE);

        private final java.awt.Color color;

        Color(java.awt.Color color) {
            this.color = color;
        }

        public java.awt.Color getColor(){
            return color;
        }
    }

}
