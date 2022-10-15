package hidenseek.model.entities;

import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.OneTimeLifeComponentImpl;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public final class PowerUp extends AbstractEntity {
    
    public PowerUp(final PowerUpType type) {
        super();
        this.attach(new OneTimeLifeComponentImpl());
        PositionComponent position = new PositionComponentImpl();
        position.setPosition(new Point2D(0, 0));
        this.attach(position);
        
        
        //Attach a component that handles collision
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, entity) -> {
            if (Player.class.isInstance(entity)) {
                event.getSender().getComponent(LifeComponent.class).get().hurt(1);
                //Effect on player
                type.effect.accept(entity);
            }
        });
        //Attach the trigger to the collision component
    }
}
