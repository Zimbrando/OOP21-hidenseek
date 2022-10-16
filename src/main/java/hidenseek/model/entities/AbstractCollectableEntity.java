package hidenseek.model.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.InventoryComponent;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.OneTimeLifeComponentImpl;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public abstract class AbstractCollectableEntity extends AbstractEntity implements CollectableEntity {

    public AbstractCollectableEntity(final int hitboxSize) {
        LifeComponent life = new OneTimeLifeComponentImpl();
        this.attach(life);
        
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, hitboxSize));
        collisionComponent.addHitboxPoint(new Point2D(hitboxSize, hitboxSize));
        collisionComponent.addHitboxPoint(new Point2D(hitboxSize, 0));
        this.attach(collisionComponent);
        
        Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, entity) -> {
            if (Player.class.isInstance(event.getCollider())) {
                life.hurt(1);
                event.getCollider().getComponent(InventoryComponent.class).ifPresent(inventory -> inventory.addCollectible(this));
            }
        });
        
        ObservableComponent collisionObservable = (ObservableComponent) collisionComponent;
        collisionObservable.attachListener(collisionListener);
    }
}
