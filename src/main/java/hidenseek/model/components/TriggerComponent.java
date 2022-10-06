package hidenseek.model.components;

import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public interface TriggerComponent<E extends Event> extends Component {

    void mapEvent(BiConsumer<E, Entity> action);
    
    void removeEvent();
    
    void notifyEvent(E event);
}
