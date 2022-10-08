package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.events.Event;

public abstract class AbstractObservableComponent extends AbstractComponent implements ObservableComponent {

    Optional<Trigger<? extends Event>> listener = Optional.empty();
   
    public <E extends Event> void attachListener(Trigger<E> tc) {
        this.listener = Optional.ofNullable(tc);
    }
    
    @Override
    public void detachListener() {
        this.listener = Optional.empty();
    }
    
    public void notifyListener(Event event) {
        try {
            this.listener.ifPresent(tg -> tg.notifyEvent(event));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
