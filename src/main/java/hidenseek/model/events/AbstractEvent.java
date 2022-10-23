package hidenseek.model.events;

import hidenseek.model.Triggerable;

public abstract class AbstractEvent implements Event {

    private final Triggerable sender;
    
    public AbstractEvent(final Triggerable sender) {
        this.sender = sender;
    }
    
    @Override
    public Triggerable getSender() {
        return this.sender;
    }

}
