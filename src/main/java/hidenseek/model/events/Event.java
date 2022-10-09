package hidenseek.model.events;

import hidenseek.model.entities.Entity;

public interface Event {
    
    /**
     * 
     * @return the @link{Entity} that generated the event
     */
    Entity getSender();
}
