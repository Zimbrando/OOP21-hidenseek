package hidenseek.model.components.brains;

import static hidenseek.utils.Utils.distanceBetween;

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
        // move
        move(targetPosition.get());
    }

    private Optional<Point2D> getRandomReachablePosition(final Set<Entity> entities) {
        //TODO use entities (walls)
        return Optional.empty();
    }
}
