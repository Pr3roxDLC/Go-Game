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

    public static enum Color{
        BLACK,WHITE
    }

}
