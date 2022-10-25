package hidenseek.model.components.physics;

import java.util.LinkedHashSet;
import java.util.Set;

import javafx.geometry.Point2D;

public class HitboxImpl implements Hitbox {

    private final Set<Point2D> points;
    private Point2D bounds;
    
    public HitboxImpl() {
        this.points = new LinkedHashSet<Point2D>();
        this.bounds = new Point2D(0, 0);
    }
    
    @Override
    public Set<Point2D> getPoints() {
        return this.points;
    }

    @Override
    public void addPoint(final Point2D point) {
        if(points.contains(point)) {
            return;
        }
        points.add(point);
        
        double boundsX = points.stream().max((x, y) -> {
            if(x.getX() == y.getX()) {
                return 0;
            }
            return x.getX() > y.getX() ? 1 : -1;
        }).get().getX();
        
        double boundsY = points.stream().max((x, y) -> {
            if(x.getY() == y.getY()) {
                return 0;
            }
            return x.getY() > y.getY() ? 1 : -1;
        }).get().getY();
        
        this.bounds = new Point2D(boundsX,  boundsY);
    }

    @Override
    public Point2D getBounds() {
        return this.bounds;
    }

}