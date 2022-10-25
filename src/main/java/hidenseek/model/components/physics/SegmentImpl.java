package hidenseek.model.components.physics;

import javafx.geometry.Point2D;

public class SegmentImpl implements Segment{

    private final Point2D point1;
    private final Point2D point2;
    
    public SegmentImpl(final Point2D point1, final Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public Point2D getPoint1() {
        return point1;
    }
    
    @Override
    public Point2D getPoint2() {
        return point2;
    }

    @Override
    public Point2D intersectingTo(final Segment segment) {        
        final Point2D s1p1 = getPoint1();
        final Point2D s1p2 = getPoint2();
        final Point2D s2p1 = segment.getPoint1();
        final Point2D s2p2 = segment.getPoint2();
        
        //calculate segments slope
        final int dy1 = (int)s1p2.getY() - (int)s1p1.getY();
        final int dx1 = (int)s1p1.getX() - (int)s1p2.getX();
        final int c1 = dy1 * (int)s1p1.getX() + dx1 * (int)s1p1.getY();
        final int dy2 = (int)s2p2.getY() - (int)s2p1.getY();
        final int dx2 = (int)s2p1.getX() - (int)s2p2.getX();
        final int c2 = dy2 * (int)s2p1.getX() + dx2 * (int)s2p1.getY();
        
        //determinate if segments are parallel - intersection impossible
        double det = dy1 * dx2 - dy2 * dx1;
        if (det == 0d)
        {
            return null;
        }
        
        //intersection point between infinite lines
        double x = (dx2 * c1 - dx1 * c2) / det;
        double y = (dy1 * c2 - dy2 * c1) / det;
        Point2D point = new Point2D(x, y);
        
        //if intersection point is inside one of two segments, they intersects
        if (pointOnLine(s1p1, s1p2, point) && pointOnLine(s2p1, s2p2, point)) {            
            return point;
        }
    
        return null; 
    }
    
    private Boolean pointOnLine(final Point2D segmentPoint1, final Point2D segmentPoint2, final Point2D point) {
        return (Math.min(segmentPoint1.getX(), segmentPoint2.getX()) <= point.getX()) && 
               (Math.max(segmentPoint1.getX(), segmentPoint2.getX()) >= point.getX()) && 
               (Math.min(segmentPoint1.getY(), segmentPoint2.getY()) <= point.getY()) && 
               (Math.max(segmentPoint1.getY(), segmentPoint2.getY()) >= point.getY());
    }
}