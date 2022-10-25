package hidenseek.model.components.physics;

import javafx.geometry.Point2D;

public interface Segment{

    /**
     * @return segment's first point
     */
    public Point2D getPoint1();

    /**
     * @return segment's second point
     */
    public Point2D getPoint2();
    
    /**
     * @param segment
     * @returns the point of intersection between this segment and the target segment
     */
    public Point2D intersectingTo(Segment segment);
}