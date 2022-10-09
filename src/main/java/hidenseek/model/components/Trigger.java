package hidenseek.model.components;

import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public interface Trigger<E extends Event> {

    /**
     * @param action, callback executed when notified
     */
    void assignCallback(BiConsumer<E, Entity> action);
    
    /**
     * Removes the current callback 
     */
    void removeCallback();
    
    /**
     * Executes the callback currently assigned
     * 
     * @param event, the event notified by the publisher
     */
    void notifyEvent(Event event);
    
    /**
     * 
     * @return the trigger event class
     */
    Class<E> getEventType();
    
}
