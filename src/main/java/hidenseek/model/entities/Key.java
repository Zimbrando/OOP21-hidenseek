package hidenseek.model.entities;

import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import javafx.geometry.Point2D;

public class Key extends AbstractCollectableEntity {

    private static final int HITBOX_SIZE = 30;
    
    public Key(final Point2D pos) {
        super(HITBOX_SIZE);
        PositionComponent position = new PositionComponentImpl();
        position.setPosition(pos);
        this.attach(position);
    }

}
