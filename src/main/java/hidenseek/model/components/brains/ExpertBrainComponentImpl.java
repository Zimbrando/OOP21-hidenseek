package hidenseek.model.components.brains;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Force;
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
public class ExpertBrainComponentImpl extends AbstractBrainComponentImpl implements BrainComponent {

    // next position useful when no targets are reachable
    private Optional<Point2D> targetPosition;
    private Set<Entity> cells;
    
    public ExpertBrainComponentImpl() {
        // define dependencies
        super();
        this.targetPosition = Optional.empty();
        this.cells = Set.of();
        
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
            // generate random random position
            if(targetPosition.isEmpty()) {
                this.targetPosition = getRandomReachablePosition(entities);
            }
        } else {
            // get target position
            final PositionComponent posTarget = target.get().getComponent(PositionComponent.class).get();
            // update target position
            this.targetPosition = Optional.of(posTarget.getPosition());
        }
        // if no movement is possible
        if(targetPosition.isEmpty()) {
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
        
        if(targetPosition.get().getX() > position.getPosition().getX()) {
            moveXDirection = Direction.RIGHT;
            moveXIntensity = 2.5;
        } else if(targetPosition.get().getX() < position.getPosition().getX()) {
            moveXDirection = Direction.LEFT;
            moveXIntensity = 2.5;
        }
        if(targetPosition.get().getY() > position.getPosition().getY()) {
            moveYDirection = Direction.DOWN;
            moveYIntensity = 2.5;
        } else if(targetPosition.get().getY() < position.getPosition().getY()) {
            moveYDirection = Direction.UP;
            moveYIntensity = 2.5;
        }

        movement.removeForce(force -> force.getIdentifier().startsWith("ai"));
        movement.addForce(new Force("ai-horizontal", moveXIntensity, moveXDirection.getValue()));
        movement.addForce(new Force("ai-vertical", moveYIntensity, moveYDirection.getValue()));
        findShortestPath(target.orElse(new Player()), entities);
        
    }

    private Optional<Point2D> getRandomReachablePosition(final Set<Entity> entities) {
        //TODO use entities (walls)
        
        
        return Optional.empty();
    }
    
    private void findShortestPath(final Entity target, final Set<Entity> entities) {
        
        // get all materials
        Set<Entity> materials = Stream.concat(Stream.of(this.getOwner().get()), entities.stream()
        .filter(e -> e.getComponent(PositionComponent.class).isPresent())
        .filter(e -> e.getComponent(MaterialComponent.class).isPresent()))
        .collect(Collectors.toSet());
        
        
//        materials.forEach(System.out::println);
        
        // get all materials positions
        Set<Point2D> positions = materials.stream()
                .map(e -> e.getComponent(PositionComponent.class).get().getPosition())
                .collect(Collectors.toSet());
        
        
        // check it there no materials
        if(positions.isEmpty()) {
            return;
        }

//        positions.forEach(p -> System.out.println(p));
        

            // bottom right point
        final Point2D brPoint = positions.stream().reduce(new Point2D(Integer.MIN_VALUE,Integer.MIN_VALUE), (p1,p2) -> new Point2D(Math.max(p1.getX(), p2.getX()),Math.max(p1.getY(), p2.getY())));

            // upper left point
        final Point2D ulPoint = positions.stream().reduce(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), (p1,p2) -> new Point2D(Math.min(p1.getX(), p2.getX()),Math.min(p1.getY(), p2.getY())));
        
        
        final int rowCols = 5;
        
        // 
        final int xJump = Math.max(1,(int)(brPoint.getX() - ulPoint.getX()) / rowCols);
        final int yJump = Math.max(1,(int)(brPoint.getY() - ulPoint.getY()) / rowCols);

        // generate grid position of enities sensed
        final Set<Point2D> cells = Stream.iterate(new Point2D(ulPoint.getX()-xJump, ulPoint.getY()-yJump), p -> new Point2D(p.getX()+xJump, ulPoint.getY()-yJump))
            .limit(rowCols+2)
            .flatMap(p -> Stream.iterate(p, p2 -> new Point2D(p2.getX(), p2.getY()+yJump)).limit(rowCols+2))
            .collect(Collectors.toSet());
        

//        cells.forEach(c -> System.out.println("before:"+ c));

        // remove grid position where there is a material entity
        this.cells = cells.stream()
            .map(c -> {
                    // create fake entity
                    return new Wall(c, Set.of(new Point2D(0,0), new Point2D(xJump,0), new Point2D(xJump,yJump), new Point2D(0,yJump) ));
                    
             })
            .filter(e->{
                    // check if all materials don't collide with this cell
                if(e.getComponent(CollisionComponent.class).get().collisionWith(target)) {
                    return true;
                }
                if(e.getComponent(CollisionComponent.class).get().collisionWith(this.getOwner().get())) {
                    return true;
                }
                    return materials.stream().allMatch(m -> !m.getComponent(CollisionComponent.class).get().collisionWith(e));
            })
            .collect(Collectors.toSet());
        
        // 
//        cells.forEach(System.out::println);
        SimpleGraph<Entity, Integer> graph;
//        cells.stream().map(e -> graph.addVertex(e));
        
        
        


//        cells.forEach(p -> System.out.println(p));
        
        
    }
        
    @Override
    public Set<Entity> cells() {
        // TODO Auto-generated method stub
        return this.cells;
    }
    
}

