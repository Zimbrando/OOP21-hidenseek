package hidenseek.model.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.OneTimeLifeComponentImpl;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.enums.Heart;
import hidenseek.model.enums.PowerUpType;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public final class PowerUp extends AbstractEntity {
    
    private static final int HITBOX_SIZE = 30;
    
    public PowerUp(final PowerUpType type, final Point2D pos) {
        super();
        
        this.attach(new OneTimeLifeComponentImpl());
        PositionComponent position = new PositionComponentImpl();
        position.setPosition(pos);
        this.attach(position);

        //Trigger for collisions
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, powerup) -> {
            Entity collider = event.getCollider();
            if (collider.getComponent(HeartComponent.class).isPresent() 
                    && collider.getComponent(HeartComponent.class).get().getHeart() == Heart.GOOD
                    && this.getComponent(LifeComponent.class).get().isAlive()) {
                
                powerup.getComponent(LifeComponent.class).get().hurt(1);
                //Effect on player
                type.effect.accept(event.getCollider());
            }
        });
        
        //HitBox
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, HITBOX_SIZE));
        collisionComponent.addHitboxPoint(new Point2D(HITBOX_SIZE, HITBOX_SIZE));
        collisionComponent.addHitboxPoint(new Point2D(HITBOX_SIZE, 0));
        
        //Attach listener
        ObservableComponent collisionObserver = (ObservableComponent) collisionComponent;
        collisionObserver.attachListener(collisionListener);
        this.attach(collisionComponent);
    }
}
