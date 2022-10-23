package hidenseek.model.entities;

import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.MapComponent;
import hidenseek.model.components.MapComponentImpl;
import hidenseek.model.components.ObservableComponent;
import hidenseek.model.components.Trigger;
import hidenseek.model.components.TriggerImpl;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.brains.ExpertBrainComponentImpl;
import hidenseek.model.components.brains.NaiveBrainComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.LinearMovementComponentImpl;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MaterialComponentImpl;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.senses.SightSenseComponentImpl;
import hidenseek.model.events.CollisionEvent;
import javafx.geometry.Point2D;

public class Monster extends AbstractEntity {
    
    private static final double MONSTER_SPEED = 250;
    
    public Monster(final Point2D position) {
        super();
        
        //Life component
        final LifeComponent lifeComponent = new LifeComponentImpl(1);
        this.attach(lifeComponent);
        
        //Position component
        final PositionComponent positionComponent = new PositionComponentImpl();
        positionComponent.setPosition(position);
        this.attach(positionComponent);
        
        //Move component
        final MoveComponent moveComponent = new LinearMovementComponentImpl(MONSTER_SPEED);
        this.attach(moveComponent);
        
        //Material component
        final MaterialComponent materialComponent = new MaterialComponentImpl();
        this.attach(materialComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, 40));
        collisionComponent.addHitboxPoint(new Point2D(40, 40));
        collisionComponent.addHitboxPoint(new Point2D(40, 0));
        this.attach(collisionComponent);  
        
        //Trigger for collisions (Hurt if I touch the player)
        final Trigger<CollisionEvent> collisionListener = new TriggerImpl<>(CollisionEvent.class, (event, powerup) -> {
            this.getComponent(HeartComponent.class).ifPresent(c -> {
                // check if hated
                if(c.hates(event.getCollider())) {
                    event.getCollider().getComponent(LifeComponent.class).ifPresent(component -> component.hurt(1));
                }
            });
        });
        
        final ObservableComponent collisionObserver = (ObservableComponent) collisionComponent;
        collisionObserver.attachListener(collisionListener);
        
        // heart
        final HeartComponent heart = new EvilHeartComponentImpl();
        this.attach(heart);

        // sight sense
        final SenseComponent sight = new SightSenseComponentImpl(2000);
        this.attach(sight);

        // brain
        final BrainComponent brain = new ExpertBrainComponentImpl();
        this.attach(brain);
//        
//        // map
        final MapComponent map = new MapComponentImpl();
        this.attach(map);
    }
}
