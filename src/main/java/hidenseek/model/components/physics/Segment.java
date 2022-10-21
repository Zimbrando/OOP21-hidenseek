package hidenseek.model.components.physics;

import javafx.geometry.Point2D;

final public class Segment{

    private final Point2D point1;
    private final Point2D point2;
    
    public Segment(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public Point2D getPoint2() {
        return point2;
    }
    
    public Point2D intersectingTo(final Segment segment) {
        
        final Point2D l1p1 = getPoint1();
        final Point2D l1p2 = getPoint2();
        final Point2D l2p1 = segment.getPoint1();
        final Point2D l2p2 = segment.getPoint2();
        
        int DY1 = (int)l1p2.getY() - (int)l1p1.getY();
        int DX1 = (int)l1p1.getX() - (int)l1p2.getX();
        int C1 = DY1 * (int)l1p1.getX() + DX1 * (int)l1p1.getY();
        int DY2 = (int)l2p2.getY() - (int)l2p1.getY();
        int DX2 = (int)l2p1.getX() - (int)l2p2.getX();
        int C2 = DY2 * (int)l2p1.getX() + DX2 * (int)l2p1.getY();
        
        //lines are parallel
        double det = DY1 * DX2 - DY2 * DX1;
        if (det == 0d)
        {
            return null; //parallel lines
        }
        
        double x = (DX2 * C1 - DX1 * C2) / det;
        double y = (DY1 * C2 - DY2 * C1) / det;
        Boolean online1 = (Math.min(l1p1.getX(), l1p2.getX()) <= x) && (Math.max(l1p1.getX(), l1p2.getX()) >= x) && (Math.min(l1p1.getY(), l1p2.getY()) <= y) && (Math.max(l1p1.getY(), l1p2.getY()) >= y);
        Boolean online2 = (Math.min(l2p1.getX(), l2p2.getX()) <= x) && (Math.max(l2p1.getX(), l2p2.getX()) >= x) && (Math.min(l2p1.getY(), l2p2.getY()) <= y) && (Math.max(l2p1.getY(), l2p2.getY()) >= y);
        
        if (online1 && online2) {            
            return new Point2D(x, y);
        }
    
        return null; 
    }
    
    public Boolean consecutiveTo(final Segment segment) {
        return getPoint1() == segment.getPoint1() || 
                getPoint2() == segment.getPoint1() || 
                getPoint1() == segment.getPoint2() || 
                getPoint2() == segment.getPoint2();
    }
    
    public Boolean adjacentTo(final Segment segment) {        
        return containsPoint(segment.getPoint1()) || containsPoint(segment.getPoint2()) || segment.containsPoint(getPoint1()) || segment.containsPoint(getPoint2());
    }
    
    private Boolean containsPoint(Point2D point) {

        final double t = 0; //tolerance
        
        
        // ensure points are collinear
        double zero = (getPoint2().getX() - getPoint1().getX()) * (point.getY() - getPoint1().getY()) - (point.getX() - getPoint1().getX()) * (getPoint2().getY() - getPoint1().getY());
        if (zero > t || zero < -t) return false;

        // check if x-coordinates are not equal
        if (getPoint1().getX() - getPoint2().getX() > t || getPoint2().getX() - getPoint1().getX() > t)
            // ensure x is between getPoint1().getX() & getPoint2().getX() (use tolerance)
            return getPoint1().getX() > getPoint2().getX()
                ? point.getX() + t > getPoint2().getX() && point.getX() - t < getPoint1().getX()
                : point.getX() + t > getPoint1().getX() && point.getX() - t < getPoint2().getX();

        // ensure y is between getPoint1().getY() & getPoint2().getY() (use tolerance)
        return getPoint1().getY() > getPoint2().getY()
            ? point.getY() + t > getPoint2().getY() && point.getY() - t < getPoint1().getY()
            : point.getY() + t > getPoint1().getY() && point.getY() - t < getPoint2().getY();
            
            
/*

        final double epsilon = 1; //tolerance
        final double crossproduct = (point.getY() - point1.getY()) * (point2.getX() - point1.getX()) - (point.getX() - point1.getX()) * (point2.getY() - point1.getY());

        if(Math.abs(crossproduct) > epsilon) {
            return false;
        }

        final double dotproduct = (point.getX() - point1.getX()) * (point2.getX() - point1.getX()) + (point.getY() - point1.getY())*(point2.getY() - point1.getY());
        if(dotproduct < 0) {
            return false;
        }

        final double squaredlengthba = (point2.getX() - point1.getX())*(point2.getX() - point1.getX()) + (point2.getY() - point1.getY())*(point2.getY() - point1.getY());
        if(dotproduct > squaredlengthba) {
           // return false;
        }

        return true;*/
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[" + point1.toString() + "; " + point2.toString() + "]";
    }
}