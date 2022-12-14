package hidenseek.model.events;

import hidenseek.model.entities.Entity;

/**
 * Base class for an Event
 */
public abstract class AbstractEvent implements Event {

    private final Entity sender;
    
    public AbstractEvent(final Entity sender) {
        this.sender = sender;
    }
    
    @Override
    public Entity getSender() {
        return this.sender;
    }

}
