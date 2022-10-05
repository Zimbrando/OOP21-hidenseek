package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.events.Event;

public abstract class AbstractObservableComponent extends ComponentImpl implements ObservableComponent {

    Optional<TriggerComponent> listener = Optional.empty();
    
    @Override
    public void attachListener(TriggerComponent tc) {
        this.listener = Optional.ofNullable(tc);
    }

    @Override
    public void detachListener() {
        this.listener = Optional.empty();
    }
    
    public void notifyListener(Event e) {
        this.listener.ifPresent(tg -> tg.notifyEvent(e));
    }
    
}
