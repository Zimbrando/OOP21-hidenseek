package hidenseek.model.events;

import hidenseek.model.entities.Entity;

/**
 * Event thrown when an Entity collides with another Entity
 */
public class CollisionEvent extends AbstractEvent {

    final private Entity collider;
    
    public CollisionEvent(final Entity sender, final Entity collider) {
        super(sender);
        this.collider = collider;
    }
    
    public Entity getCollider() {
        return this.collider;
    }

}
