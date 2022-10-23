package hidenseek.model.components.brains;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Component;
import hidenseek.model.components.Force;
import hidenseek.model.components.MapComponent;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.Direction;
import hidenseek.model.enums.Heart;
import javafx.geometry.Point2D;

import static hidenseek.model.components.Utils.distanceBetween;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public class ExpertBrainComponentImpl extends AbstractBrainComponent implements BrainComponent {

    // next position useful when no targets are reachable
    private Optional<Point2D> targetPosition;
    private Set<Entity> cells;
    private Set<Entity> path;
    private Optional<Point2D> targetCell;
    private Optional<Point2D> sourceCell;
    
    public ExpertBrainComponentImpl() {
        // define dependencies
        super();
        this.targetPosition = Optional.empty();
        this.cells = Set.of();
        this.path = Set.of();
        this.targetCell = Optional.empty();
        this.sourceCell = Optional.empty();
        
        // Set heart.GOOD behavior
        // TODO set Heart.GOOD behavior

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
        // if no path is found
        if(nextPosition.isEmpty()) {
            return;
        }
        // get movement component
        final PositionComponent position = this.getOwner().get().getComponent(PositionComponent.class).get();
        final MoveComponent movement = this.getOwner().get().getComponent(MoveComponent.class).get();
        // move based on target position

        double moveXIntensity = 0;
        Direction moveXDirection = Direction.DOWN;
        
        double moveYIntensity = 0;
        Direction moveYDirection = Direction.DOWN;
        
        if(nextPosition.get().getX() > position.getPosition().getX()) {
            moveXDirection = Direction.RIGHT;
            moveXIntensity = 2.5;
        } else if(nextPosition.get().getX() < position.getPosition().getX()) {
            moveXDirection = Direction.LEFT;
            moveXIntensity = 2.5;
        }
        if(nextPosition.get().getY() > position.getPosition().getY()) {
            moveYDirection = Direction.DOWN;
            moveYIntensity = 2.5;
        } else if(nextPosition.get().getY() < position.getPosition().getY()) {
            moveYDirection = Direction.UP;
            moveYIntensity = 2.5;
        }


        movement.removeForce(force -> force.getIdentifier().startsWith("ai"));
        movement.addForce(new Force("ai-horizontal", moveXIntensity, moveXDirection.getValue()));
        movement.addForce(new Force("ai-vertical", moveYIntensity, moveYDirection.getValue()));

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
        Entity owner = this.getOwner().get();
        // check map
        if(owner.getComponent(MapComponent.class).isEmpty()) {
            return Optional.empty();
        }
        // get map
        MapComponent map = owner.getComponent(MapComponent.class).get();
        // put entities in map
        map.mapEntities(entities);
        // get map points
        final Set<Point2D> gameMap = map.getGameMap();
        //TODO delete, just for debug
        this.cells = gameMap.stream()
                .map(p -> new Wall(p, Set.of(new Point2D(-10, -10), new Point2D(10, -10), new Point2D(10, 10), new Point2D(-10, 10))))
                .collect(Collectors.toSet());
        
        // get nearest point to target in map
        final Point2D targetPos = findNearestPoint(target.getComponent(PositionComponent.class).get().getPosition(), gameMap).get();
        
        // get nearest point to me in map
        final Point2D myPos = findNearestPoint(owner.getComponent(PositionComponent.class).get().getPosition(), gameMap).get();
        
        // find shortest path points
        final Set<Point2D> path = map.getPath(myPos, targetPos);
        //TODO delete, just for debug
        this.path = path.stream()
                .map(p -> new Wall(p, Set.of(new Point2D(-10, -10), new Point2D(10, -10), new Point2D(10, 10), new Point2D(-10, 10))))
                .collect(Collectors.toSet());
        
        return findNearestPoint(myPos, path.stream().filter(p -> p.distance(myPos)>50).collect(Collectors.toSet()));
    }

    private Optional<Point2D> findNearestPoint(final Point2D source, final Set<Point2D> points) {
        return points.stream().reduce((p1,p2) -> (p1.distance(source) < p2.distance(source)) ? p1 :p2);
    }
    
    @Override
    public Set<Entity> cells() {
        // TODO Auto-generated method stub
        return this.cells;
    }

    @Override
    public Set<Entity> path() {
        // TODO Auto-generated method stub
        return this.path;
    }
    
}

