package hidenseek.model.events;

import hidenseek.model.Entity;

public abstract class AbstractEvent implements Event {

    private final Entity sender;
    
    public AbstractEvent(Entity sender) {
        this.sender = sender;
    }
    
    @Override
    public Entity getSender() {
        return this.sender;
    }

}
