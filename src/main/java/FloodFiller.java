import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class FloodFiller {

    public static boolean foundWithoutNeighbour = false;

    public enum Offsets {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        final int x;
        final int y;

        Offsets(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point offsetPoint(Point point) {
            return new Point(point.x + x, point.y + y);
        }
    }

    //Takes the position of a stone and flags all getStones() that will get surrounded by the placement of this stone
    public static void removeStonesFromCounter(int x, int y) {
        foundWithoutNeighbour = false;
        Point point = new Point(x, y);
        Stone.Color originColor = Main.getStones()[x][y].getColor();
        //Check for the 4 neighbours of the original stone
        for (Offsets offset : Offsets.values()) {
            Point offsetPoint = offset.offsetPoint(point);
            //Check if the neighbour point is not empty
            try{
                if (Main.getStones()[offsetPoint.x][offsetPoint.y] != null) {
                    //Check if the neighbour point is not the same color as our initial point
                    if (Main.getStones()[offsetPoint.x][offsetPoint.y].getColor() != originColor) {
                        //Get all Stones that will become surrounded by the placement of the stone
                        System.out.println("Removing Stones");
                        getAllSurroundedStones(Main.getStones()[offsetPoint.x][offsetPoint.y].getColor(), offsetPoint, new ArrayList<>(Collections.singleton(offsetPoint))).forEach(n -> {
                            //Remove all surrounded getStones() from the score counter
                            if (!foundWithoutNeighbour) {
                                System.out.println(n);
                                Main.getStones()[n.x][n.y].setCounted(false);
                            }
                        });
                    }
                }
            }catch (Exception ignored){}
        }

    }

    //Returns a boolean if it has all neighbours
    public static ArrayList<Point> getAllSurroundedStones(Stone.Color color, Point point, ArrayList<Point> checkedPoints) {
        ArrayList<Point> points = new ArrayList<>();
        //Get all surrounding points
        checkedPoints.add(point);
        Offsets[] values = Offsets.values();
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            Offsets offset = values[i];
            Point offsetPoint = offset.offsetPoint(point);
            //Check if stone is there
            try{
                if (Main.getStones()[offsetPoint.x][offsetPoint.y] != null) {
                    //Check if stone is of the same color
                    if (Main.getStones()[offsetPoint.x][offsetPoint.y].getColor() == color && !checkedPoints.contains(offsetPoint)) {
                        //repeat for all neighbours, and add all the resulting points to our list
                        getAllSurroundedStones(color, offsetPoint, checkedPoints).forEach(n -> {
                            if(!points.contains(n))points.add(n);
                        });
                    }
                } else {
                    foundWithoutNeighbour = true;
                }
            }catch (Exception ignored){}
        }
        //return the list
        checkedPoints.addAll(points);
        return checkedPoints;
    }

}
