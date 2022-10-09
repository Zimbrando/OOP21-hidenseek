package hidenseek.test.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hidenseek.model.entities.Entity;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.events.CollisionEvent;
import hidenseek.model.events.DamageEvent;

public class TriggerTest {
    
    @Test public void testTriggerComponent() {
        final Entity e = new AbstractEntity(){};
        final int damage = 10;
        final ObservableComponent life = new LifeComponentImpl(100);
        life.attachListener(new TriggerImpl<DamageEvent>(DamageEvent.class, 
                (event, entity) -> assertEquals(damage, event.getDamage())));
        e.attach(life);
        final LifeComponent compLife = (LifeComponent) life;
        compLife.hurt(damage);
    }
    
    //TODO fix multiple triggers test (needs a component that can throw different events)
    @Test public void testMultipleTriggers() {
        final int damage = 10;
        final Entity e = new AbstractEntity(){};
        final Trigger<DamageEvent> damageListener = new TriggerImpl<>(DamageEvent.class);
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class);
        final ObservableComponent publisher = new LifeComponentImpl(100);
        publisher.attachListener(damageListener);
        publisher.attachListener(collisionListener);
        damageListener.assignCallback((event, source) -> assertEquals(damage, event.getDamage()));
        collisionListener.assignCallback((event, source) -> assertTrue(true));
        e.attach(publisher);
        final LifeComponent life = (LifeComponent) publisher;
        life.hurt(damage);
    }
    
}
