package hidenseek.model.components;

import hidenseek.model.Trigger;
import hidenseek.model.events.Event;

/**
 * Component that can be observed by subscribers known as {@link Trigger}s
 */
public interface ObservableComponent extends Component {
     
    /**
     * @param listener
     *          subscriber for a specific <E> event
     */
    <E extends Event> void attachListener(Trigger<E> listener);
    
    /**
     * Removes the subscriber for a specific event type
     * @param eventType
     */
    <E extends Event> void detachListener(Class<E> eventType);
}
