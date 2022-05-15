import java.awt.*;

public class Button {

    private Runnable runnable = null;
    private final Rectangle rectangle;
    private Color color;
    private final String text;
    private Color textColor;

    private boolean active = true;

    public Button(Rectangle rectangle, Color color, String string, Runnable runnable){
        this(rectangle, color, string);
        this.runnable = runnable;
    }

    public Button(Rectangle rectangle, Color color, String string){
        this.rectangle = rectangle;
        this.color = color;
        this.textColor = getContrastColor(color);
        this.text = string;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.textColor = getContrastColor(color);
    }

    public String getText() {
        return text;
    }

    public Color getTextColor() {
        return textColor;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void click(){
        if(runnable!=null) {
            runnable.run();
        }else{
            System.out.println("Tried running Button runnable before setting it");
        }
    }


    public static Color getContrastColor(Color color) {
        double y = ((299 * color.getRed()) + 587 * color.getGreen() + (114 * color.getBlue())) / 1000f;
        return y >= 128 ? Color.black : Color.white;
    }

}
