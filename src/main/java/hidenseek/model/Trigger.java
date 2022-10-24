package hidenseek.model;

import java.util.function.BiConsumer;

import hidenseek.model.components.ObservableComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.events.Event;

/**
 * Subscriber to {@link ObservableComponent}
 * @param <E>
 *              The Event that this trigger listens to
 */
public interface Trigger<E extends Event> {

    /**
     * @param action
     *          Callback executed when notified
     */
    void assignCallback(BiConsumer<E, Entity> action);
    
    /**
     * Removes the current callback 
     */
    void removeCallback();
    
    /**
     * Executes the callback currently assigned
     * 
     * @param event 
     *          The event notified by the publisher
     */
    void notifyEvent(Event event);
    
    /**
     * 
     * @return The trigger event class
     */
    Class<E> getEventType();
    
}
