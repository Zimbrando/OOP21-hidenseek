package hidenseek.model.components;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;

final public class CollisionComponentImpl extends AbstractObservableComponent implements CollisionComponent {
    
    private final Set<Point2D> hitbox;
    
    public CollisionComponentImpl() {
        this.hitbox = new LinkedHashSet<Point2D>();
    }
    
    @Override
    public Set<Point2D> getHitbox() {
        return hitbox;
    }

    @Override
    public void addHitboxPoint(Point2D point) {
        if(hitbox.contains(point)) {
            return;
        }
        hitbox.add(point);
    }

    @Override
    public void removeHitboxPoint(Point2D point) {
        hitbox.remove(point);
    }

    @Override
    public Boolean collisionWith(Entity entity) {
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
                
                if(getIntersectionPoint(prevEntityPoint, currEntityPoint, prevOwnPoint, currOwnPoint) != null) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private Point2D getIntersectionPoint(Point2D l1p1, Point2D l1p2, Point2D l2p1, Point2D l2p2)
    {
        double A1 = l1p2.getY() - l1p1.getY();
        double B1 = l1p1.getX() - l1p2.getX();
        double C1 = A1 * l1p1.getX() + B1 * l1p1.getY();
        double A2 = l2p2.getY() - l2p1.getY();
        double B2 = l2p1.getX() - l2p2.getX();
        double C2 = A2 * l2p1.getX() + B2 * l2p1.getY();
        
        //lines are parallel
        double det = A1 * B2 - A2 * B1;
        if (det == 0d)
        {
            return null; //parallel lines
        }
        
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;
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
            return new Point2D(x, y);
    
        return null; 
    }


    
}
