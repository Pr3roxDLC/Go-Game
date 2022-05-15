import java.awt.*;

public class Button {

    private Runnable runnable = null;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Color getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    private Rectangle rectangle = null;
    private Color color = null;
    private String text = "";

    public Color textColor = null;

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

    public void setRunnable(Runnable runnable){
        this.runnable = runnable;
    }

    public void click(){
        if(runnable!=null) {
            runnable.run();
        }else{
            System.out.println("Tried running Button runnable before setting it");
        }
    }

    public void setColor(Color color) {
        this.color = color;
        this.textColor = getContrastColor(color);
    }

    public static Color getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }

}
