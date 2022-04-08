import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

import static me.pr3.catcher.Catcher.TRY;

public class FloodFiller {

    public static boolean foundNeighbourless = false;

    public static enum Offsets {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        int x = 0;
        int y = 0;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        Offsets(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point offsetPoint(Point point) {
            return new Point(point.x + x, point.y + y);
        }
    }

    //Takes the position of a stone and flags all stones that will get surrounded by the placement of this stone
    public static void removeStonesFromCounter(int x, int y) {
        foundNeighbourless = false;
        Point point = new Point(x, y);
        Stone.Color originColor = Main.stones[x][y].getColor();
        //Check for the 4 neighbours of the original stone
        for (Offsets offset : Offsets.values()) {
            Point offsetPoint = offset.offsetPoint(point);
            //Check if the neighbour point is not empty
            TRY(() -> {
                if (Main.stones[offsetPoint.x][offsetPoint.y] != null) {
                    //Check if the neighbour point is not the same color as our initial point
                    if (Main.stones[offsetPoint.x][offsetPoint.y].getColor() != originColor) {
                        //Get all Stones that will become surrounded by the placement of the stone
                        System.out.println("Removing Stones");
                        getAllSurroundedStones(Main.stones[offsetPoint.x][offsetPoint.y].getColor(), offsetPoint, new ArrayList<>(Collections.singleton(offsetPoint))).forEach(n -> {
                            //Remove all surrounded stones from the score counter
                            if (!foundNeighbourless) {
                                System.out.println(n);
                                Main.stones[n.x][n.y].setCounted(false);
                            }
                        });
                    }
                }
            });
        }

    }

    //Returns a boolean if it has all neighbours
    public static ArrayList<Point> getAllSurroundedStones(Stone.Color color, Point point, ArrayList<Point> checkedPoints) {
        ArrayList<Point> points = new ArrayList<>();
        //Get all surrounding points
        checkedPoints.add(point);
        for (Offsets offset : Offsets.values()) {
            Point offsetPoint = offset.offsetPoint(point);
            //Check if stone is there
            TRY(() -> {
                if (Main.stones[offsetPoint.x][offsetPoint.y] != null) {
                    //Check if stone is of the same color
                    if (Main.stones[offsetPoint.x][offsetPoint.y].getColor() == color && !checkedPoints.contains(offsetPoint)) {
                        //repeat for all neighbours, and add all the resulting points to our list
                        points.addAll(getAllSurroundedStones(color, offsetPoint, checkedPoints));
                    }
                } else {
                    foundNeighbourless = true;
                }
            });
        }
        //return the list
        checkedPoints.addAll(points);
        return checkedPoints;
    }

}
