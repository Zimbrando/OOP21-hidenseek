package hidenseek.model.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MaterialComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.brains.NaiveBrainComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.senses.SightSenseComponentImpl;
import javafx.geometry.Point2D;

public class Monster extends AbstractEntity {
    
    public Monster() {
        super();
        
        this.attach(new LifeComponentImpl(1));  
        
        //Position component
        final PositionComponent positionComponent = new PositionComponentImpl();
        positionComponent.setPosition(new Point2D(0, 0));
        this.attach(positionComponent);
        
        //Move component
        final MoveComponent moveComponent = new LinearMovementComponentImpl();
        this.attach(moveComponent);
        
        //Material component
        final MaterialComponent materialComponent = new MaterialComponentImpl();
        this.attach(materialComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, 50));
        collisionComponent.addHitboxPoint(new Point2D(50, 50));
        collisionComponent.addHitboxPoint(new Point2D(50, 0));
        this.attach(collisionComponent);        
        
        // heart
        final HeartComponent heart = new EvilHeartComponentImpl();
        this.attach(heart);

        // sight sense
        final SenseComponent sight = new SightSenseComponentImpl(200);
        this.attach(sight);
        
        // brain sense
        final BrainComponent brain = new NaiveBrainComponentImpl();
        this.attach(brain);
    }
}
