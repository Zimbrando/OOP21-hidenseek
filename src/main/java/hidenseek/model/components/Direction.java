package hidenseek.model.components;

import javafx.geometry.Point2D;

public enum Direction {
    RIGHT(new Point2D(1, 0)), 
    LEFT(new Point2D(-1, 0)), 
    UP(new Point2D(0, -1)), 
    DOWN(new Point2D(0, 1));

    Point2D point;
    
    Direction(Point2D point) {
        this.point = point;
    }
};
