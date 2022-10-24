package hidenseek.model.components.brains;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.MapComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.Heart;
import javafx.geometry.Point2D;

import static hidenseek.utils.Utils.distanceBetween;
import static hidenseek.utils.Utils.upperLeft;
import static hidenseek.utils.Utils.bottomRight;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public class ExpertBrainComponentImpl extends AbstractBrainComponent {

    // next position useful when no targets are reachable
    private Optional<Point2D> targetPosition;
    
    public ExpertBrainComponentImpl() {
        // define dependencies
        super();
        this.targetPosition = Optional.empty();
        
        // Set heart.GOOD behavior
        // TODO set Heart.GOOD behavior
        setBehaviour(Heart.GOOD
                , e  -> e.getComponent(PositionComponent.class).isPresent()
                        && this.getOwner().get().getComponent(HeartComponent.class).get().loves(e)
                , (e1,e2) -> distanceBetween(this.getOwner().get(),e1) < distanceBetween(this.getOwner().get(),e2) ? e1 : e2
                , (e1, setE) -> moveTowards(e1, setE));

        // Set heart.EVIL behavior
        setBehaviour(Heart.EVIL
                , e  -> e.getComponent(HeartComponent.class).isPresent()
                        && e.getComponent(PositionComponent.class).isPresent()
                        && this.getOwner().get().getComponent(HeartComponent.class).get().hates(e)
                , (e1,e2) -> distanceBetween(this.getOwner().get(),e1) < distanceBetween(this.getOwner().get(),e2) ? e1 : e2
                , (e1, setE) -> moveTowards(e1, setE));
    }
    
    /**
     * Naive movement. It just move towards the target, regardless of walls.
     * If no target is provide it moves randomly
     * @param target
     */
    private void moveTowards(final Optional<Entity> target, final Set<Entity> entities) {

        //check target
        if(target.isEmpty()) {
            // generate random position
            if(targetPosition.isEmpty()) {
                this.targetPosition = getRandomReachablePosition(entities);
            }
        } else {
            // get target position
            final PositionComponent posTarget = target.get().getComponent(PositionComponent.class).get();
            // update target position
            this.targetPosition = Optional.of(posTarget.getPosition());
        }
        // if no target position is found
        if(targetPosition.isEmpty()) {
            return;
        }
        // find shortest path to target position
        final Optional<Point2D> nextPosition = findShortestPath(target.orElse(
                            new Wall(targetPosition.get(), Set.of(new Point2D(-10, -10), new Point2D(10, -10), new Point2D(10, 10), new Point2D(-10, 10))))
                            , entities
                );
        // if no path is found - maybe no walls are found
        if(nextPosition.isEmpty()) {
            move(targetPosition.get());
            return;
        }
        // move avoiding walls
        move(nextPosition.get());
    }

    private Optional<Point2D> getRandomReachablePosition(final Set<Entity> entities) {
        //TODO use entities (walls)
        return Optional.empty();
    }
    
    /**
     * Find shortest path to reach target, 
     * avoiding entities that respect the avoidCheck predicate.
     * @return
     */
    private Optional<Point2D> findShortestPath(final Entity target, final Set<Entity> entities) {
        // get owner
        final Entity owner = this.getOwner().get();
        // check map
        if(owner.getComponent(MapComponent.class).isEmpty()) {
            return Optional.empty();
        }
        // get map
        final MapComponent map = owner.getComponent(MapComponent.class).get();
        // put entities in map
        map.mapEntities(entities);
        // get map points
        final Set<Point2D> gameMap = map.getGameMap();
        
        // get nearest point to target in map
        final Optional<Point2D> optTargetPos = findNearestPoint(target.getComponent(PositionComponent.class).get().getPosition(), gameMap);
        if(optTargetPos.isEmpty()) {
            return Optional.empty();
        }
        Point2D targetPos = optTargetPos.get();
        
        // get nearest point to me in map
        final Optional<Point2D> optMyPos = findNearestPoint(owner.getComponent(PositionComponent.class).get().getPosition(), gameMap);
        if(optMyPos.isEmpty()) {
            return Optional.empty();
        }
        Point2D myPos = optMyPos.get();
        
        // find shortest path points
        final Set<Point2D> path = map.getPath(myPos, targetPos);

        // get hitbox
        final Set<Point2D> hitbox = owner.getComponent(CollisionComponent.class).get().getHitbox();
        
        // bottom right point
        final Point2D brPoint = bottomRight(hitbox);

        // upper left point
        final Point2D ulPoint = upperLeft(hitbox);
                
        // find nearest point in path, after hitbox width
        return findNearestPoint(myPos, path.stream().filter(p -> p.distance(myPos)>brPoint.getX()-ulPoint.getX()).collect(Collectors.toSet()));
    }

    /**
     * Find nearest point in set of point
     * @param source current position
     * @param points set of point to search
     * @return the nearest point, empty otherwise
     */
    private Optional<Point2D> findNearestPoint(final Point2D source, final Set<Point2D> points) {
        return points.stream().reduce((p1,p2) -> p1.distance(source) < p2.distance(source) ? p1 :p2);
    }
    
}

