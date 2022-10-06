package hidenseek.model.events;

import hidenseek.model.Entity;

public class CollisionEvent extends AbstractEvent {

    public CollisionEvent(Entity sender) {
        super(sender);
    }

}
