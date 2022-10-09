package hidenseek.model.events;

import hidenseek.model.entities.Entity;

public class CollisionEvent extends AbstractEvent {

    public CollisionEvent(final Entity sender) {
        super(sender);
    }

}
