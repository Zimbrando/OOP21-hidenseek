package hidenseek.model.events;

import hidenseek.model.Triggerable;

/**
 * Event exchanged between {@link ObservableComponent}s and {@link Trigger}s
 */
public interface Event {
    
    /**
     * @return The @link{Triggerable} that generated the event
     */
    Triggerable getSender();
}
