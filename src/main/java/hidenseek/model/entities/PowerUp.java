package hidenseek.model.entities;

import hidenseek.model.Trigger;
import hidenseek.model.TriggerImpl;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.enums.Heart;
import hidenseek.model.enums.PowerUpType;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.lives.LifeComponent;
import hidenseek.model.components.lives.OneTimeLifeComponentImpl;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public final class PowerUp extends AbstractEntity {
    
    private static final int HITBOX_SIZE = 30;
    
    public PowerUp(final PowerUpType type, final Point2D pos) {
        super();
        
        this.attach(new OneTimeLifeComponentImpl());
        final PositionComponent position = new PositionComponentImpl();
        position.setPosition(pos);
        this.attach(position);

        //Trigger for collisions
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, powerup) -> {
            final Entity collider = event.getCollider();
            if (collider.getComponent(HeartComponent.class).isPresent() 
                    && collider.getComponent(HeartComponent.class).get().getHeart() == Heart.GOOD
                    && this.getComponent(LifeComponent.class).get().isAlive()) {
                
                ((Entity)powerup).getComponent(LifeComponent.class).get().hurt(1);
                //Effect on player
                type.getEffect().accept(event.getCollider());
            }
        });
        
        //HitBox
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.getHitbox().addPoint(new Point2D(0, 0));
        collisionComponent.getHitbox().addPoint(new Point2D(0, HITBOX_SIZE));
        collisionComponent.getHitbox().addPoint(new Point2D(HITBOX_SIZE, HITBOX_SIZE));
        collisionComponent.getHitbox().addPoint(new Point2D(HITBOX_SIZE, 0));
        
        //Attach listener
        final ObservableComponent collisionObserver = (ObservableComponent) collisionComponent;
        collisionObserver.attachListener(collisionListener);
        this.attach(collisionComponent);
    }
}
