package hidenseek.model.entities;

import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.OneTimeLifeComponentImpl;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public final class PowerUp extends AbstractEntity {
    
    public PowerUp(final PowerUpType type) {
        super();
        this.attach(new OneTimeLifeComponentImpl());
        this.attach(new PositionComponentImpl(new Point2D(0, 0)));
        
        //Attach a component that handles collision
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, entity) -> {
            if (Player.class.isInstance(entity)) {
                event.getSender().getComponent(LifeComponent.class).get().hurt(1);
                //Effect on player
                type.effect.accept(event, entity);
            }
        });
        //Attach the trigger to the collision component
    }
}
