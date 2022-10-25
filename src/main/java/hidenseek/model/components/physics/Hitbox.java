package hidenseek.model.components.physics;

import java.util.Set;

import javafx.geometry.Point2D;

public interface Hitbox {
   
    /**
     * @return the hitbox points
     */
    Set<Point2D> getPoints();
    
    /**
     * Add point to hitbox
     * @param point
     */
    void addPoint(Point2D point);
    
    /**
     * @return the total space occupied by the hitbox's owner
     */
    Point2D getBounds();
}