package hidenseek.model.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import hidenseek.model.Entity;
import hidenseek.model.events.DamageEvent;
import hidenseek.model.events.Event;

public class TriggerComponentImpl extends ComponentImpl implements TriggerComponent {

    private final Map<Class<? extends Event>, BiConsumer<? extends Event, Entity>> slots = 
            new LinkedHashMap<Class<? extends Event>, BiConsumer<? extends Event, Entity>>();

    @Override
    public <E extends Event> void mapEvent(Class<E> event, BiConsumer<E, Entity> action) {
        this.slots.put(event, action);
    }
    
    @Override
    public <E extends Event> void removeEvent(Class<E> event) {
        this.slots.remove(event);
    }

    @Override
    public void notifyEvent(Event event) {
        System.out.println(DamageEvent.class.cast(event).getDamage());
    }
}
