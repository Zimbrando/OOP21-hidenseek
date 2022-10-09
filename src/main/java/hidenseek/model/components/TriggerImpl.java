package hidenseek.model.components;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public class TriggerImpl<E extends Event> implements Trigger<E> {

    private final Class<E> eventType;
    private Optional<BiConsumer<E, Entity>> action = Optional.empty();
    
    public TriggerImpl(final Class<E> eventType) {
        this.eventType = eventType;
    }
    
    public TriggerImpl(final Class<E> eventType, final BiConsumer<E, Entity> action) {
        this.eventType = eventType;
        this.action = Optional.ofNullable(action);
    }
    
    @Override
    public void notifyEvent(final Event event) {
        if (eventType.isInstance(event)) {
            this.action.ifPresent(action -> action.accept(eventType.cast(event), event.getSender()));        
        }
    }

    @Override
    public void assignCallback(final BiConsumer<E, Entity> action) {
        this.action = Optional.ofNullable(action);
    }

    @Override
    public void removeCallback() {
        this.action = Optional.empty();
    }

    @Override
    public Class<E> getEventType() {
        return this.eventType;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(action, this.eventType);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof TriggerImpl))
            return false;
        TriggerImpl<?> other = (TriggerImpl<?>) obj;
        return Objects.equals(action, other.action);
    }
}
