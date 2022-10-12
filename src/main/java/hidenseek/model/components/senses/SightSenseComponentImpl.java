package hidenseek.model.components.senses;

import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.SenseConfidence;
import javafx.geometry.Point2D;

public class SightSenseComponentImpl extends AbstractSenseComponent{
    
    final private int sightRange;
    
    public SightSenseComponentImpl(final int sightRange) {
        super();
        this.sightRange = sightRange;
    }

    @Override
    SenseConfidence senseCheck(final Entity e) {
        // check owner
        if(this.getOwner().isEmpty()) {
            return SenseConfidence.NONE;
        }
        // check owner position component
        final Entity owner = this.getOwner().get();
        if(owner.getComponent(PositionComponent.class).isEmpty()) {
            return SenseConfidence.NONE;
        }
        // check entity position component
        if(e.getComponent(PositionComponent.class).isEmpty()) {
            return SenseConfidence.NONE;
        }
        // check if position is into range
        final Point2D ownerPos = owner.getComponent(PositionComponent.class).get().getPosition();
        final Point2D ePos = e.getComponent(PositionComponent.class).get().getPosition();
        if(ownerPos.distance(ePos) > this.sightRange) {
            return SenseConfidence.NONE;
        }
        // TODO calibrate senseconfidence based on distance
        return SenseConfidence.HIGH;
    }
}
