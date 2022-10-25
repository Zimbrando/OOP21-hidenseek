package hidenseek.model.events;

import hidenseek.model.entities.Entity;

/**
 * Event exchanged between {@link hidenseek.model.components.ObservableComponent}s and {@link hidenseek.model.Trigger}s
 */
public interface Event {
    
    /**
     * @return The @link{Entity} that generated the event
     */
    Entity getSender();
}
