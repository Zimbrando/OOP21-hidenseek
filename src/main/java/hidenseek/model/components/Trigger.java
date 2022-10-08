package hidenseek.model.components;

import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public interface Trigger<E extends Event> {

    void mapEvent(BiConsumer<E, Entity> action);
    
    void removeEvent();
    
    void notifyEvent(Event event);
    
    Class<E> getEventType();
    
}
