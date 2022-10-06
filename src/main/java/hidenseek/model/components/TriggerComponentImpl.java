package hidenseek.model.components;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.Event;

public class TriggerComponentImpl<E extends Event> extends AbstractComponent implements TriggerComponent<E> {

    private Optional<Class<E>> eventType = Optional.empty();
    private Optional<BiConsumer<E, Entity>> action = Optional.empty();
    
    public TriggerComponentImpl(Class<E> eventType) {
        this.eventType = Optional.of(eventType);
    }
    
    @Override
    public void notifyEvent(E event) {
        this.action.ifPresent(action -> action.accept(event, event.getSender()));
    }

    @Override
    public void mapEvent(BiConsumer<E, Entity> action) {
        this.action = Optional.ofNullable(action);
    }

    @Override
    public void removeEvent() {
        this.action = Optional.empty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(action, this.eventType);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof TriggerComponentImpl))
            return false;
        TriggerComponentImpl<?> other = (TriggerComponentImpl<?>) obj;
        return Objects.equals(action, other.action);
    }
}
