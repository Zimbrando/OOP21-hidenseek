package hidenseek.model.components;

import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public interface TriggerComponent extends Component {

    <E extends Event> void mapEvent(Class<E> event, BiConsumer<E, Entity> action);
    
    <E extends Event> void removeEvent(Class<E> event);
    
    void notifyEvent(Event event);
}
