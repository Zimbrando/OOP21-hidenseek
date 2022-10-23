package hidenseek.model.components.physics;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

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
    public void removeHitboxPoint(final Point2D point) {
        hitbox.remove(point);
    }

    @Override
    public void onCollision(final Entity entity) {
        this.notifyListener(new CollisionEvent(this.getOwner().get(), entity), CollisionEvent.class);
    }

    @Override
    public void onNear(final Entity entity) {
        this.notifyListener(new CollisionEvent(this.getOwner().get(), entity), CollisionEvent.class);
    }

    @Override
    public Boolean collisionTo(final Entity entity, final Point2D ownOffset) {
        return segmentsMatch(ownOffset, entity, new Point2D(0, 0), (a, b) -> {
            Point2D intersectionPoint = a.intersectingTo(b);
            if(intersectionPoint == null) {
                return false;
            }
            if(a.getPoint1().equals(intersectionPoint) || a.getPoint2().equals(intersectionPoint) || b.getPoint1().equals(intersectionPoint) || b.getPoint2().equals(intersectionPoint)) {
                return false;
            }
            return true;
        });
    }
    
    @Override
    public Boolean nearTo(Entity entity, final Point2D ownOffset) {
        return segmentsMatch(ownOffset, entity, new Point2D(0, 0), (a, b) -> {
            Point2D intersectionPoint = a.intersectingTo(b);
            if(intersectionPoint == null) {
                return false;
            }
            if(a.getPoint1().equals(intersectionPoint) || a.getPoint2().equals(intersectionPoint) || b.getPoint1().equals(intersectionPoint) || b.getPoint2().equals(intersectionPoint)) {
                return true;
            }
            return false;
        });
    }
    
    @Override
    public Boolean collisionOrNearTo(Entity entity, final Point2D ownOffset) {
        return nearTo(entity, ownOffset) || collisionTo(entity, ownOffset);
    }
    
    private Boolean segmentsMatch(final Point2D ownOffset, final Entity entity, final Point2D entityOffset, BiPredicate<Segment, Segment> condition) {

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
            Point2D prevEntityPoint = (i == 0 ? entityHitbox[entityHitbox.length-1] : entityHitbox[i-1]).add(entityPosition).add(entityOffset);
            Point2D currEntityPoint = entityHitbox[i].add(entityPosition).add(entityOffset);
            Segment entitySegment = new Segment(prevEntityPoint, currEntityPoint);
            
            for(int j=0; j<ownHitbox.length; j++) {
                Point2D prevOwnPoint = (j == 0 ? ownHitbox[ownHitbox.length-1] : ownHitbox[j-1]).add(ownPosition).add(ownOffset);
                Point2D currOwnPoint = ownHitbox[j].add(ownPosition).add(ownOffset);
                Segment ownSegment = new Segment(prevOwnPoint, currOwnPoint);
                
                if(condition.test(entitySegment, ownSegment)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
