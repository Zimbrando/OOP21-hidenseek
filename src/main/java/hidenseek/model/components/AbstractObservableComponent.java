package hidenseek.model.components;

import java.util.HashSet;
import java.util.Set;

import hidenseek.model.events.Event;

public abstract class AbstractObservableComponent extends AbstractComponent implements ObservableComponent {

    private final Set<Trigger<? extends Event>> listeners = new HashSet<>();
    private final Set<Class<? extends Event>> currentAttachedListeners = new HashSet<>();
    
    public <E extends Event> void attachListener(final Trigger<E> trigger) {
        this.listeners.add(trigger);
        this.currentAttachedListeners.add(trigger.getEventType());
    }
    
    public <E extends Event> void detachListener(final Class<E> eventType) {
        this.listeners.removeIf(trigger -> trigger.getEventType().equals(eventType));
        this.currentAttachedListeners.remove(eventType);
    }
    
    /**
     * Notify the listeners attached that an event occured
     * @param event
     * @param eventType
     * @throws IllegalArgumentException, if the event thrown is not an instance of the event type given
     */
    protected void notifyListener(final Event event, final Class<? extends Event> eventType) throws IllegalArgumentException {
        if (!this.hasListener(eventType)) {
            return;
        }
        if (!eventType.isInstance(event)) {
            throw new IllegalArgumentException("Parameter 1 should be an instance of parameter 2");
        }
        this.listeners.forEach(trigger -> trigger.notifyEvent(event));
    }
    
    private <E extends Event> boolean hasListener(final Class<E> eventType) {
        return this.currentAttachedListeners.contains(eventType);
    }
    
}
