package hidenseek.model.events;

import hidenseek.model.entities.Entity;

public abstract class AbstractEvent implements Event {

    private final Object sender;
    
    public AbstractEvent(final Object sender) {
        this.sender = sender;
    }
    
    @Override
    public Object getSender() {
        return this.sender;
    }

}
