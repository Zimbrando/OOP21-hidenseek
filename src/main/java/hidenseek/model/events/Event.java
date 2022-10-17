package hidenseek.model.events;

import hidenseek.model.entities.Entity;

/**
 * Event exchanged between {@link ObservableComponent}s and {@link Trigger}s
 */
public interface Event {
    
    /**
     * @return The @link{Entity} that generated the event
     */
    Entity getSender();
}
