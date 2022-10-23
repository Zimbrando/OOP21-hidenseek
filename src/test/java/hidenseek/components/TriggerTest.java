package hidenseek.components;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hidenseek.model.entities.Entity;
import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.events.CollisionEvent;
import hidenseek.model.events.DamageEvent;

public class TriggerTest {
    
    private int count = 0;
    
    @Test public void testTriggerComponent() {
        this.count = 0;
        final Entity e = new AbstractEntity(){};
        final int damage = 10;
        final ObservableComponent life = new LifeComponentImpl(100);
        life.attachListener(new TriggerImpl<DamageEvent>(DamageEvent.class, 
                (event, entity) -> {
                    assertEquals(damage, event.getDamage());   
                    count++;
                }));
        e.attach(life);
        final LifeComponent compLife = (LifeComponent) life;
        compLife.hurt(damage);
        assertEquals(1, this.count);
    }
    
    /**
     * Test class that can throw events of different type
     */
    class DoubleEventObservableComponent extends AbstractObservableComponent {
        
        void sendDamageEvent() {
            this.notifyListener(new DamageEvent(this.getOwner().get(), 10), DamageEvent.class);
        }
        
        void sendCollisionEvent() {
            this.notifyListener(new CollisionEvent(this.getOwner().get(), this.getOwner().get()), CollisionEvent.class);
        }
    }
    
    @Test public void testMultipleTriggers() {
        this.count = 0;
        final Entity e = new AbstractEntity(){};
        final Trigger<DamageEvent> damageListener = new TriggerImpl<>(DamageEvent.class);
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class);
        final ObservableComponent publisher = new DoubleEventObservableComponent();
        publisher.attachListener(damageListener);
        publisher.attachListener(collisionListener);
        damageListener.assignCallback((event, source) -> this.count++);
        collisionListener.assignCallback((event, source) -> this.count++);
        e.attach(publisher);
        DoubleEventObservableComponent sender = (DoubleEventObservableComponent) publisher;
        sender.sendCollisionEvent();
        sender.sendDamageEvent();
        assertEquals(2, this.count);
    }
    
}
