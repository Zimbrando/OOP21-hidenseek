package hidenseek.model.components.physics;

import java.util.function.BiPredicate;
import javafx.geometry.Point2D;

import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.events.CollisionEvent;

public final class CollisionComponentImpl extends AbstractObservableComponent implements CollisionComponent {
    
    private final Hitbox hitbox;
    
    public CollisionComponentImpl() {
        this.hitbox = new HitboxImpl();
    }
    
    @Override
    public Hitbox getHitbox() {
        return hitbox;
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
            final Point2D intersectionPoint = a.intersectingTo(b);
            if(intersectionPoint == null) {
                return false;
            }
            
            return !a.getPoint1().equals(intersectionPoint) && 
                   !a.getPoint2().equals(intersectionPoint) &&
                   !b.getPoint1().equals(intersectionPoint) &&
                   !b.getPoint2().equals(intersectionPoint);
        });
    }
    
    @Override
    public Boolean nearTo(final Entity entity, final Point2D ownOffset) {
        return segmentsMatch(ownOffset, entity, new Point2D(0, 0), (a, b) -> {
            final Point2D intersectionPoint = a.intersectingTo(b);
            if(intersectionPoint == null) {
                return false;
            }

            return a.getPoint1().equals(intersectionPoint) ||
                   a.getPoint2().equals(intersectionPoint) ||
                   b.getPoint1().equals(intersectionPoint) ||
                   b.getPoint2().equals(intersectionPoint);
        });
    }
    
    @Override
    public Boolean collisionOrNearTo(final Entity entity, final Point2D ownOffset) {
        return nearTo(entity, ownOffset) || collisionTo(entity, ownOffset);
    }
    
    private Boolean segmentsMatch(final Point2D ownOffset, final Entity entity, final Point2D entityOffset, final BiPredicate<Segment, Segment> condition) {

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
        
        final Point2D entityPosition = entity.getComponent(PositionComponent.class).get().getPosition();
        final Point2D[] entityHitbox = entity.getComponent(CollisionComponent.class).get().getHitbox().getPoints().toArray(new Point2D[0]);
        
        final Point2D ownPosition = getOwner().get().getComponent(PositionComponent.class).get().getPosition();
        final Point2D[] ownHitbox = getOwner().get().getComponent(CollisionComponent.class).get().getHitbox().getPoints().toArray(new Point2D[0]);
        
        //test condition for every combination of segments between own hitbox and entity hitbox        
        for(int i=0; i<entityHitbox.length; i++) {
            final Point2D prevEntityPoint = (i == 0 ? entityHitbox[entityHitbox.length-1] : entityHitbox[i-1]).add(entityPosition).add(entityOffset);
            final Point2D currEntityPoint = entityHitbox[i].add(entityPosition).add(entityOffset);
            final Segment entitySegment = new SegmentImpl(prevEntityPoint, currEntityPoint);
            
            for(int j=0; j<ownHitbox.length; j++) {
                final Point2D prevOwnPoint = (j == 0 ? ownHitbox[ownHitbox.length-1] : ownHitbox[j-1]).add(ownPosition).add(ownOffset);
                final Point2D currOwnPoint = ownHitbox[j].add(ownPosition).add(ownOffset);
                final Segment ownSegment = new SegmentImpl(prevOwnPoint, currOwnPoint);
                
                if(condition.test(entitySegment, ownSegment)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
