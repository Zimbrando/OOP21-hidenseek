package hidenseek.model.enums;

import javafx.geometry.Point2D;

public enum Direction {
    RIGHT(new Point2D(1, 0)), 
    LEFT(new Point2D(-1, 0)), 
    UP(new Point2D(0, -1)), 
    DOWN(new Point2D(0, 1));

    public Point2D point;
    
    Direction(final Point2D point) {
        this.point = point;
    }
}