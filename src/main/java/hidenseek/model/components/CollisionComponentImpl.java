package hidenseek.model.components;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

final public class CollisionComponentImpl extends AbstractObservableComponent implements CollisionComponent {
    
    private final Set<Point2D> hitbox;
    private double boundsX = 0;
    private double boundsY = 0;
    
    public CollisionComponentImpl() {
        this.hitbox = new LinkedHashSet<Point2D>();
    }
    
    @Override
    public Set<Point2D> getHitbox() {
        return hitbox;
    }
    
    

    @Override
    public Point2D getBounds() {
        return new Point2D(boundsX, boundsY);
    }

    @Override
    public void addHitboxPoint(Point2D point) {
        if(hitbox.contains(point)) {
            return;
        }
        hitbox.add(point);
        
        boundsX = hitbox.stream().max((x, y) -> {
            if(x.getX() == y.getX()) {
                return 0;
            }
            return x.getX() > y.getX() ? 1 : -1;
        }).get().getX();
        
        boundsY = hitbox.stream().max((x, y) -> {
            if(x.getY() == y.getY()) {
                return 0;
            }
            return x.getY() > y.getY() ? 1 : -1;
        }).get().getY();
        
    }

    @Override
    public void removeHitboxPoint(Point2D point) {
        hitbox.remove(point);
    }

    @Override
    public Boolean collisionWith(Entity entity) {

//        final Double points[] = (Double[])hitbox.stream().flatMap(p -> Stream.of(p.getX(), p.getY())).collect(Collectors.toList()).toArray();
//        Polygon poly = new Polygon();
//        poly.getPoints().addAll(points);
//        Shape a = new Polygon();
//        
        return willCollisionWith(entity, new Point2D(0,0));
    }
    

    @Override
    public Boolean willCollisionWith(Entity entity, Point2D offset) {
        if(getOwner().isPresent() && getOwner().get() == entity) {
            return false;
        }
        if(!getOwner().isPresent() || !getOwner().get().getComponent(PositionComponent.class).isPresent()) {
            return false;
        }
        if(entity == null) {
            return false;
        }
        if(!entity.getComponent(PositionComponent.class).isPresent()) {
            return false;
        }
        if(!entity.getComponent(CollisionComponent.class).isPresent()) {
            return false;
        }
        
        Point2D entityPosition = entity.getComponent(PositionComponent.class).get().getPosition();
        Point2D[] entityHitbox = entity.getComponent(CollisionComponent.class).get().getHitbox().toArray(new Point2D[0]);
        
        Point2D ownPosition = getOwner().get().getComponent(PositionComponent.class).get().getPosition();
        Point2D[] ownHitbox = getOwner().get().getComponent(CollisionComponent.class).get().getHitbox().toArray(new Point2D[0]);
        
        for(int i=0; i<entityHitbox.length; i++) {
            Point2D prevEntityPoint = (i == 0 ? entityHitbox[entityHitbox.length-1] : entityHitbox[i-1]).add(entityPosition);
            Point2D currEntityPoint = entityHitbox[i].add(entityPosition);
            
            for(int j=0; j<ownHitbox.length; j++) {
                Point2D prevOwnPoint = (j == 0 ? ownHitbox[ownHitbox.length-1] : ownHitbox[j-1]).add(ownPosition).add(offset);
                Point2D currOwnPoint = ownHitbox[j].add(ownPosition).add(offset);
                
                if(prevEntityPoint.getX() == currEntityPoint.getX() && prevOwnPoint.getX() == currOwnPoint.getX() && prevEntityPoint.getX() == prevOwnPoint.getX()) {
                    //System.out.println("");
                }
                
                if(prevEntityPoint.getY() == currEntityPoint.getY() && prevOwnPoint.getY() == currOwnPoint.getY() && prevEntityPoint.getY() == prevOwnPoint.getY()) {
                    //System.out.println("");
                }
                
                if(getIntersectionPoint(prevEntityPoint, currEntityPoint, prevOwnPoint, currOwnPoint)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private Boolean getIntersectionPoint(Point2D l1p1, Point2D l1p2, Point2D l2p1, Point2D l2p2)
    {
        
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
            return false; //parallel lines
        }
        
        double x = (DX2 * C1 - DX1 * C2) / det;
        double y = (DY1 * C2 - DY2 * C1) / det;
        Boolean online1 = 
            (Math.min(l1p1.getX(), l1p2.getX()) < x || Math.min(l1p1.getX(), l1p2.getX()) == x) &&
            (Math.max(l1p1.getX(), l1p2.getX()) > x || Math.max(l1p1.getX(), l1p2.getX()) == x) &&
            (Math.min(l1p1.getY(), l1p2.getY()) < y || Math.max(l1p1.getY(), l1p2.getY()) == y) &&
            (Math.max(l1p1.getY(), l1p2.getY()) > y || Math.max(l1p1.getY(), l1p2.getY()) == y);
        
        Boolean online2 = 
            (Math.min(l2p1.getX(), l2p2.getX()) < x || Math.min(l2p1.getX(), l2p2.getX()) == x) &&
            (Math.max(l2p1.getX(), l2p2.getX()) > x || Math.max(l2p1.getX(), l2p2.getX()) == x) &&
            (Math.min(l2p1.getY(), l2p2.getY()) < y || Math.max(l2p1.getY(), l2p2.getY()) == y) &&
            (Math.max(l2p1.getY(), l2p2.getY()) > y || Math.max(l2p1.getY(), l2p2.getY()) == y);
        
        if (online1 && online2)
            return true;
    
        return false; 
    }


    
}
