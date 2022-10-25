package hidenseek.model.entities;

import hidenseek.model.Trigger;
import hidenseek.model.TriggerImpl;
import hidenseek.model.components.InventoryComponent;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.lives.LifeComponent;
import hidenseek.model.components.lives.OneTimeLifeComponentImpl;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.enums.Heart;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public abstract class AbstractCollectableEntity extends AbstractEntity implements CollectableEntity {

    public AbstractCollectableEntity(final int hitboxSize) {
        final LifeComponent life = new OneTimeLifeComponentImpl();
        this.attach(life);
        
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.getHitbox().addPoint(new Point2D(0, 0));
        collisionComponent.getHitbox().addPoint(new Point2D(0, hitboxSize));
        collisionComponent.getHitbox().addPoint(new Point2D(hitboxSize, hitboxSize));
        collisionComponent.getHitbox().addPoint(new Point2D(hitboxSize, 0));
        this.attach(collisionComponent);
        
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, entity) -> {
            final Entity collider = event.getCollider();
            if (collider.getComponent(HeartComponent.class).isPresent() 
                    && collider.getComponent(HeartComponent.class).get().getHeart() == Heart.GOOD) {
                life.hurt(1);
                event.getCollider().getComponent(InventoryComponent.class).ifPresent(inventory -> inventory.addCollectible(this));
            }
        });
        
        final ObservableComponent collisionObservable = (ObservableComponent) collisionComponent;
        collisionObservable.attachListener(collisionListener);
    }
}
