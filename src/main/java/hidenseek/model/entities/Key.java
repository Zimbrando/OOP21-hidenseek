package hidenseek.model.entities;

import hidenseek.model.components.RewardComponent;
import hidenseek.model.components.RewardComponentImpl;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import javafx.geometry.Point2D;

public final class Key extends AbstractCollectableEntity {

    private static final int HITBOX_SIZE = 30;
    
    public Key(final Point2D position) {
        super(HITBOX_SIZE);
        final PositionComponent positionComponent = new PositionComponentImpl();
        positionComponent.setPosition(position);
        this.attach(positionComponent);

        final RewardComponent reward = new RewardComponentImpl(1);
        this.attach(reward);
    }

}
