package hidenseek.model.components.brains;

import java.util.Optional;
import java.util.Set;

import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.Force;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Direction;
import hidenseek.model.enums.Heart;
import javafx.geometry.Point2D;

import static hidenseek.model.components.Utils.distanceBetween;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public class NaiveBrainComponentImpl extends AbstractBrainComponent implements BrainComponent {

    // next position useful when no targets are reachable
    private Optional<Point2D> targetPosition;
    
    public NaiveBrainComponentImpl() {
        // define dependencies
        super();
        this.targetPosition = Optional.empty();
        
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
            moveXIntensity = 1;
        } else {
            moveXDirection = Direction.LEFT;
            moveXIntensity = 1;
        }
        if(targetPosition.get().getY() > position.getPosition().getY()) {
            moveYDirection = Direction.DOWN;
            moveYIntensity = 1;
        } else {
            moveYDirection = Direction.UP;
            moveYIntensity = 1;
        }

        movement.removeForce(force -> force.getIdentifier().startsWith("ai"));
        movement.addForce(new Force("ai-horizontal", moveXIntensity, moveXDirection.getValue()));
        movement.addForce(new Force("ai-vertical", moveYIntensity, moveYDirection.getValue()));
    }

    private Optional<Point2D> getRandomReachablePosition(final Set<Entity> entities) {
        //TODO use entities (walls)
        return Optional.empty();
    }
    
    @Override
    public Set<Entity> cells() {
        return Set.of();
    }
    
    @Override
    public Set<Entity> path() {
        return Set.of();
    }

    
}
