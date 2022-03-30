public class Stone {

    private final Color color;

    public Stone(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public static enum Color{
        BLACK,WHITE
    }

}
